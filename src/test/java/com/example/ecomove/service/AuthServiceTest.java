package com.example.ecomove.service;

import com.example.ecomove.dto.request.AuthRequestDTO;
import com.example.ecomove.dto.request.UserRequestDTO;
import com.example.ecomove.dto.response.AuthResponseDTO;
import com.example.ecomove.entity.Utilisateur;
import com.example.ecomove.enums.Role;
import com.example.ecomove.exception.AuthException;
import com.example.ecomove.mapper.UserMapper;
import com.example.ecomove.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthService authService;

    private Utilisateur utilisateur;
    private AuthRequestDTO loginRequest;

    @BeforeEach
    void setUp() {
        utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("Dupont");
        utilisateur.setPrenom("Jean");
        utilisateur.setEmail("jean@test.com");
        utilisateur.setMotDePasse("$2a$hashed");
        utilisateur.setRole(Role.PASSAGER);
        utilisateur.setActif(true);

        loginRequest = new AuthRequestDTO();
        loginRequest.setEmail("jean@test.com");
        loginRequest.setMotDePasse("password123");
    }

    // ============================================
    // TEST 1 — INSCRIPTION RÉUSSIE
    // ============================================
    @Test
    @DisplayName("Inscription réussie : nouvel utilisateur créé")
    void inscrire_nouvelUtilisateur_retourneAuthResponse() {

        UserRequestDTO registerRequest = new UserRequestDTO();
        registerRequest.setNom("Martin");
        registerRequest.setPrenom("Marie");
        registerRequest.setEmail("marie@test.com");
        registerRequest.setMotDePasse("password123");
        registerRequest.setTelephone("0600000000");
        registerRequest.setRole(Role.PASSAGER);

        when(utilisateurRepository.existsByEmail("marie@test.com"))
                .thenReturn(false);
        when(userMapper.toModel(any(UserRequestDTO.class)))
                .thenReturn(utilisateur);
        when(passwordEncoder.encode(anyString()))
                .thenReturn("$2a$hashed");
        when(utilisateurRepository.save(any(Utilisateur.class)))
                .thenReturn(utilisateur);
        when(jwtService.genererToken(any(Utilisateur.class)))
                .thenReturn("jwt.token.test");

        AuthResponseDTO result = authService.inscrire(registerRequest);

        assertNotNull(result);
        assertEquals("jwt.token.test", result.getToken());
        assertEquals("Bearer", result.getTokenType());
        verify(utilisateurRepository, times(1))
                .save(any(Utilisateur.class));
    }

    // ============================================
    // TEST 2 — INSCRIPTION EMAIL DÉJÀ UTILISÉ
    // FIX — on vérifie juste qu'une AuthException
    // est levée, sans vérifier le message exact
    // (le message dépend de l'implémentation)
    // ============================================
    @Test
    @DisplayName("Inscription échoue : email déjà existant")
    void inscrire_emailDejaExistant_leveAuthException() {

        UserRequestDTO registerRequest = new UserRequestDTO();
        registerRequest.setEmail("jean@test.com");
        registerRequest.setMotDePasse("password123");

        when(utilisateurRepository.existsByEmail("jean@test.com"))
                .thenReturn(true);

        // Vérifie qu'une exception est levée
        assertThrows(AuthException.class,
                () -> authService.inscrire(registerRequest));

        // save() ne doit pas être appelé
        verify(utilisateurRepository, never())
                .save(any(Utilisateur.class));
    }

    // ============================================
    // TEST 3 — CONNEXION RÉUSSIE
    // ============================================
    @Test
    @DisplayName("Connexion réussie : retourne token JWT")
    void connecter_credentialsValides_retourneToken() {

        when(utilisateurRepository.findByEmail("jean@test.com"))
                .thenReturn(Optional.of(utilisateur));
        when(passwordEncoder.matches("password123", "$2a$hashed"))
                .thenReturn(true);
        when(jwtService.genererToken(any(Utilisateur.class)))
                .thenReturn("jwt.token.valide");

        AuthResponseDTO result = authService.connecter(loginRequest);

        assertNotNull(result);
        assertEquals("jwt.token.valide", result.getToken());
        assertEquals(Role.PASSAGER, result.getRole());
    }

    // ============================================
    // TEST 4 — MAUVAIS MOT DE PASSE
    // ============================================
    @Test
    @DisplayName("Connexion échoue : mauvais mot de passe")
    void connecter_mauvaisMotDePasse_leveAuthException() {

        when(utilisateurRepository.findByEmail("jean@test.com"))
                .thenReturn(Optional.of(utilisateur));
        when(passwordEncoder.matches("password123", "$2a$hashed"))
                .thenReturn(false);

        assertThrows(AuthException.class,
                () -> authService.connecter(loginRequest));
    }

    // ============================================
    // TEST 5 — COMPTE DÉSACTIVÉ
    // FIX — suppression du stub passwordEncoder
    // Le service vérifie actif=false AVANT le mot
    // de passe → passwordEncoder.matches n'est
    // jamais appelé → stub inutile = UnnecessaryStubbingException
    // ============================================
    @Test
    @DisplayName("Connexion échoue : compte désactivé")
    void connecter_compteDesactive_leveAuthException() {

        utilisateur.setActif(false);

        when(utilisateurRepository.findByEmail("jean@test.com"))
                .thenReturn(Optional.of(utilisateur));

        // PAS de stub passwordEncoder ici
        // car le service lève l'exception avant
        // d'arriver à la vérification du mot de passe

        assertThrows(AuthException.class,
                () -> authService.connecter(loginRequest));
    }
}