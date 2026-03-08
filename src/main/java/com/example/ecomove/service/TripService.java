package com.example.ecomove.service;

import com.example.ecomove.dto.request.TripRequestDTO;
import com.example.ecomove.dto.response.TripResponseDTO;
import com.example.ecomove.enums.TrajetStatut;
import com.example.ecomove.exception.ResourceNotFoundException;
import com.example.ecomove.mapper.TripMapper;
import com.example.ecomove.entity.Trajet;
import com.example.ecomove.entity.Utilisateur;
import com.example.ecomove.entity.Vehicule;
import com.example.ecomove.repository.TrajetRepository;
import com.example.ecomove.repository.UtilisateurRepository;
import com.example.ecomove.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService {

    @Autowired
    private TrajetRepository trajetRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private TripMapper tripMapper;

    // Créer un nouveau trajet
    public TripResponseDTO creer(
            Long conducteurId,
            TripRequestDTO dto) {

        // Vérifier que le conducteur existe
        Utilisateur conducteur =
                utilisateurRepository
                        .findById(conducteurId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Utilisateur", conducteurId));

        // Vérifier que le véhicule existe
        // et appartient au conducteur
        Vehicule vehicule =
                vehiculeRepository
                        .findById(dto.getVehiculeId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Vehicule",
                                        dto.getVehiculeId()));

        // Convertir DTO en Model
        Trajet trajet = tripMapper.toModel(dto);
        trajet.setConducteur(conducteur);
        trajet.setVehicule(vehicule);

        return tripMapper.toResponseDTO(
                trajetRepository.save(trajet));
    }

    // Récupérer tous les trajets ouverts
    public List<TripResponseDTO>
    recupererTrajetsOuverts() {

        return trajetRepository
                .findByStatut(TrajetStatut.OUVERT)
                .stream()
                .map(tripMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un trajet par id
    public TripResponseDTO recupererParId(
            Long id) {

        Trajet trajet = trajetRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Trajet", id));

        return tripMapper.toResponseDTO(trajet);
    }

    // Rechercher des trajets disponibles
    public List<TripResponseDTO> rechercher(
            String lieuDepart,
            String lieuArrivee,
            LocalDateTime date) {

        return trajetRepository
                .rechercherTrajets(
                        lieuDepart, lieuArrivee, date)
                .stream()
                .map(tripMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Annuler un trajet
    public TripResponseDTO annuler(Long id) {

        Trajet trajet = trajetRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Trajet", id));

        trajet.setStatut(TrajetStatut.ANNULE);

        return tripMapper.toResponseDTO(
                trajetRepository.save(trajet));
    }

    // Récupérer les trajets d'un conducteur
    public List<TripResponseDTO>
    recupererParConducteur(
            Long conducteurId) {

        return trajetRepository
                .findByConducteurId(conducteurId)
                .stream()
                .map(tripMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
