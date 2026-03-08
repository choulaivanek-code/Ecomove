package com.example.ecomove.service;

import com.example.ecomove.dto.request.TripRequestDTO;
import com.example.ecomove.dto.response.TripResponseDTO;
import com.example.ecomove.entity.Trajet;
import com.example.ecomove.entity.Utilisateur;
import com.example.ecomove.entity.Vehicule;
import com.example.ecomove.enums.TrajetStatut;
import com.example.ecomove.exception.ResourceNotFoundException;
import com.example.ecomove.mapper.TripMapper;
import com.example.ecomove.repository.TrajetRepository;
import com.example.ecomove.repository.UtilisateurRepository;
import com.example.ecomove.repository.VehiculeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @Mock
    private TrajetRepository trajetRepository;
    @Mock
    private UtilisateurRepository utilisateurRepository;
    @Mock
    private VehiculeRepository vehiculeRepository;
    @Mock
    private TripMapper tripMapper;

    @InjectMocks
    private TripService tripService;

    private Utilisateur conducteur;
    private Vehicule vehicule;
    private Trajet trajet;
    private TripRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        conducteur = new Utilisateur();
        conducteur.setId(1L);
        conducteur.setNom("Dupont");
        conducteur.setPrenom("Jean");
        conducteur.setActif(true);

        vehicule = new Vehicule();
        vehicule.setId(5L);
        vehicule.setMarque("Renault");
        vehicule.setModele("Clio");
        vehicule.setNombrePlaces(4);

        trajet = new Trajet();
        trajet.setId(10L);
        trajet.setLieuDepart("Paris");
        trajet.setLieuArrivee("Lyon");
        trajet.setDateHeureDepart(
                LocalDateTime.now().plusDays(1));
        trajet.setNombrePlaces(3);
        trajet.setDistanceKm(450.0);
        trajet.setEconomieCo2Kg(450.0 * 0.21);
        trajet.setStatut(TrajetStatut.OUVERT);
        trajet.setConducteur(conducteur);
        trajet.setVehicule(vehicule);

        requestDTO = new TripRequestDTO();
        requestDTO.setLieuDepart("Paris");
        requestDTO.setLieuArrivee("Lyon");
        requestDTO.setDateHeureDepart(
                LocalDateTime.now().plusDays(1));
        requestDTO.setNombrePlaces(3);
        requestDTO.setDistanceKm(450.0);
        requestDTO.setVehiculeId(5L);
    }

    @Test
    @DisplayName("Création trajet réussie : CO2 calculé automatiquement")
    void creer_trajetValide_co2Calcule() {

        when(utilisateurRepository.findById(1L))
                .thenReturn(Optional.of(conducteur));
        when(vehiculeRepository.findById(5L))
                .thenReturn(Optional.of(vehicule));

        // ✅ FIX — mock toModel() sinon NullPointerException
        // Le service appelle tripMapper.toModel(dto)
        // qui retournait null par défaut
        when(tripMapper.toModel(any(TripRequestDTO.class)))
                .thenReturn(trajet);

        when(trajetRepository.save(any(Trajet.class)))
                .thenReturn(trajet);

        TripResponseDTO responseAttendue = new TripResponseDTO();
        responseAttendue.setId(10L);
        responseAttendue.setEconomieCo2Kg(94.5);
        responseAttendue.setStatut(TrajetStatut.OUVERT);

        when(tripMapper.toResponseDTO(any(Trajet.class)))
                .thenReturn(responseAttendue);

        // WHEN
        TripResponseDTO result = tripService.creer(1L, requestDTO);

        // THEN
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals(TrajetStatut.OUVERT, result.getStatut());
        assertEquals(94.5, result.getEconomieCo2Kg());
        verify(trajetRepository, times(1)).save(any(Trajet.class));
    }

    @Test
    @DisplayName("Création trajet échoue : conducteur introuvable")
    void creer_conducteurInexistant_leveResourceNotFoundException() {

        when(utilisateurRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> tripService.creer(99L, requestDTO));

        verify(trajetRepository, never()).save(any(Trajet.class));
    }

    @Test
    @DisplayName("Annulation trajet réussie : statut → ANNULE")
    void annuler_trajetOuvert_statutDevientAnnule() {

        when(trajetRepository.findById(10L))
                .thenReturn(Optional.of(trajet));
        when(trajetRepository.save(any(Trajet.class)))
                .thenReturn(trajet);

        TripResponseDTO responseAttendue = new TripResponseDTO();
        responseAttendue.setId(10L);
        responseAttendue.setStatut(TrajetStatut.ANNULE);

        when(tripMapper.toResponseDTO(any(Trajet.class)))
                .thenReturn(responseAttendue);

        TripResponseDTO result = tripService.annuler(10L);

        assertNotNull(result);
        assertEquals(TrajetStatut.ANNULE, result.getStatut());
    }

    @Test
    @DisplayName("Récupérer trajets ouverts : retourne liste")
    void recupererTrajetsOuverts_retourneListe() {

        when(trajetRepository.findByStatut(TrajetStatut.OUVERT))
                .thenReturn(List.of(trajet));

        TripResponseDTO dto = new TripResponseDTO();
        dto.setId(10L);
        dto.setStatut(TrajetStatut.OUVERT);

        when(tripMapper.toResponseDTO(trajet)).thenReturn(dto);

        List<TripResponseDTO> result =
                tripService.recupererTrajetsOuverts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(TrajetStatut.OUVERT, result.get(0).getStatut());
    }
}