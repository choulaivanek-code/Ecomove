package com.example.ecomove.service;

import com.example.ecomove.dto.request.UserRequestDTO;
import com.example.ecomove.dto.request.AuthRequestDTO;
import com.example.ecomove.dto.response.AuthResponseDTO;
import com.example.ecomove.exception.AuthException;
import com.example.ecomove.mapper.UserMapper;
import com.example.ecomove.entity.Utilisateur;
import com.example.ecomove.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // Inscription d'un nouvel utilisateur
    public AuthResponseDTO inscrire(
            UserRequestDTO dto) {

        // Vérifier si l'email existe déjà
        if (utilisateurRepository
                .existsByEmail(dto.getEmail())) {
            throw new AuthException(
                    "Email déjà utilisé : "
                            + dto.getEmail());
        }

        // Convertir DTO en Model
        Utilisateur utilisateur =
                userMapper.toModel(dto);

        // Hasher le mot de passe avec BCrypt
        // On ne stocke JAMAIS un mot de passe
        // en clair en base de données
        utilisateur.setMotDePasse(
                passwordEncoder.encode(
                        dto.getMotDePasse()));

        // Sauvegarder en base de données
        Utilisateur sauvegarde =
                utilisateurRepository
                        .save(utilisateur);

        // Générer le token JWT
        String token = jwtService
                .genererToken(sauvegarde);

        // Construire la réponse
        AuthResponseDTO response =
                new AuthResponseDTO();
        response.setToken(token);
        response.setUserId(sauvegarde.getId());
        response.setEmail(sauvegarde.getEmail());
        response.setNom(sauvegarde.getNom());
        response.setPrenom(sauvegarde.getPrenom());
        response.setRole(sauvegarde.getRole());

        return response;
    }

    // Connexion d'un utilisateur existant
    public AuthResponseDTO connecter(
            AuthRequestDTO dto) {

        // Chercher l'utilisateur par email
        Utilisateur utilisateur =
                utilisateurRepository
                        .findByEmail(dto.getEmail())
                        .orElseThrow(() ->
                                new AuthException(
                                        "Email ou mot de passe incorrect"));

        // Vérifier si le compte est actif
        if (!utilisateur.isActif()) {
            throw new AuthException(
                    "Compte désactivé. "
                            + "Contactez l'administrateur");
        }

        // Vérifier le mot de passe
        if (!passwordEncoder.matches(
                dto.getMotDePasse(),
                utilisateur.getMotDePasse())) {
            throw new AuthException(
                    "Email ou mot de passe incorrect");
        }

        // Générer le token JWT
        String token = jwtService
                .genererToken(utilisateur);

        // Construire la réponse
        AuthResponseDTO response =
                new AuthResponseDTO();
        response.setToken(token);
        response.setUserId(utilisateur.getId());
        response.setEmail(utilisateur.getEmail());
        response.setNom(utilisateur.getNom());
        response.setPrenom(utilisateur.getPrenom());
        response.setRole(utilisateur.getRole());

        return response;
    }
}
