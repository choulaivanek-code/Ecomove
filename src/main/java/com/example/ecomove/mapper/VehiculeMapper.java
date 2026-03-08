package com.example.ecomove.mapper;


import com.example.ecomove.dto.request.VehiculeRequestDTO;
import com.example.ecomove.dto.response.VehiculeResponseDTO;
import com.example.ecomove.entity.Vehicule;
import org.springframework.stereotype.Component;

@Component
public class VehiculeMapper {

    // Convertit un Vehicule (Model)
    // en VehiculeResponseDTO
    public VehiculeResponseDTO toResponseDTO(
            Vehicule vehicule) {

        if (vehicule == null) return null;

        VehiculeResponseDTO dto =
                new VehiculeResponseDTO();

        dto.setId(vehicule.getId());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());
        dto.setImmatriculation(
                vehicule.getImmatriculation());
        dto.setNombrePlaces(
                vehicule.getNombrePlaces());
        dto.setEtat(vehicule.getEtat());

        // Infos du propriétaire
        if (vehicule.getProprietaire() != null) {
            dto.setProprietaireNom(
                    vehicule.getProprietaire().getNom());
            dto.setProprietairePrenom(
                    vehicule.getProprietaire()
                            .getPrenom());
        }

        return dto;
    }

    // Convertit un VehiculeRequestDTO
    // en Vehicule (Model)
    public Vehicule toModel(
            VehiculeRequestDTO dto) {

        if (dto == null) return null;

        Vehicule vehicule = new Vehicule();
        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setImmatriculation(
                dto.getImmatriculation());
        vehicule.setNombrePlaces(
                dto.getNombrePlaces());

        return vehicule;
    }
}
