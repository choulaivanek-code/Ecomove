package com.example.ecomove.service;

import com.example.ecomove.dto.request.VehiculeRequestDTO;
import com.example.ecomove.dto.response.VehiculeResponseDTO;
import com.example.ecomove.exception.ResourceNotFoundException;
import com.example.ecomove.exception.VehiculeException;
import com.example.ecomove.mapper.VehiculeMapper;
import com.example.ecomove.entity.Utilisateur;
import com.example.ecomove.entity.Vehicule;
import com.example.ecomove.repository.UtilisateurRepository;
import com.example.ecomove.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private VehiculeMapper vehiculeMapper;

    // Ajouter un véhicule pour un utilisateur
    public VehiculeResponseDTO ajouter(
            Long proprietaireId,
            VehiculeRequestDTO dto) {

        // Vérifier que le propriétaire existe
        Utilisateur proprietaire =
                utilisateurRepository
                        .findById(proprietaireId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Utilisateur",
                                        proprietaireId));

        // Vérifier que l'immatriculation
        // n'existe pas déjà
        if (vehiculeRepository
                .existsByImmatriculation(
                        dto.getImmatriculation())) {
            throw new VehiculeException(
                    "Immatriculation déjà enregistrée : "
                            + dto.getImmatriculation());
        }

        // Convertir DTO en Model
        Vehicule vehicule =
                vehiculeMapper.toModel(dto);
        vehicule.setProprietaire(proprietaire);

        return vehiculeMapper.toResponseDTO(
                vehiculeRepository.save(vehicule));
    }

    // Récupérer les véhicules
    // d'un utilisateur
    public List<VehiculeResponseDTO>
    recupererParProprietaire(
            Long proprietaireId) {

        return vehiculeRepository
                .findByProprietaireId(proprietaireId)
                .stream()
                .map(vehiculeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un véhicule par id
    public VehiculeResponseDTO recupererParId(
            Long id) {

        Vehicule vehicule = vehiculeRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicule", id));

        return vehiculeMapper
                .toResponseDTO(vehicule);
    }

    // Modifier un véhicule
    public VehiculeResponseDTO modifier(
            Long id, VehiculeRequestDTO dto) {

        Vehicule vehicule = vehiculeRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicule", id));

        // Vérifier si la nouvelle
        // immatriculation n'est pas déjà prise
        if (!vehicule.getImmatriculation()
                .equals(dto.getImmatriculation())
                && vehiculeRepository
                .existsByImmatriculation(
                        dto.getImmatriculation())) {
            throw new VehiculeException(
                    "Immatriculation déjà enregistrée : "
                            + dto.getImmatriculation());
        }

        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setImmatriculation(
                dto.getImmatriculation());
        vehicule.setNombrePlaces(
                dto.getNombrePlaces());

        return vehiculeMapper.toResponseDTO(
                vehiculeRepository.save(vehicule));
    }

    // Supprimer un véhicule
    public void supprimer(Long id) {

        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Vehicule", id);
        }

        vehiculeRepository.deleteById(id);
    }
}
