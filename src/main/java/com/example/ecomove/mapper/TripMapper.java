package com.example.ecomove.mapper;

import com.example.ecomove.dto.request.TripRequestDTO;
import com.example.ecomove.dto.response.TripResponseDTO;
import com.example.ecomove.entity.Trajet;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {

    // Convertit un Trajet (Model)
    // en TripResponseDTO
    public TripResponseDTO toResponseDTO(
            Trajet trajet) {

        if (trajet == null) return null;

        TripResponseDTO dto = new TripResponseDTO();
        dto.setId(trajet.getId());
        dto.setLieuDepart(trajet.getLieuDepart());
        dto.setLieuArrivee(trajet.getLieuArrivee());
        dto.setDateHeureDepart(
                trajet.getDateHeureDepart());
        dto.setNombrePlaces(trajet.getNombrePlaces());
        dto.setDistanceKm(trajet.getDistanceKm());
        dto.setEconomieCo2Kg(
                trajet.getEconomieCo2Kg());
        dto.setStatut(trajet.getStatut());

        // Calcul des places disponibles
        int reservations = trajet.getReservations()
                != null
                ? trajet.getReservations().size()
                : 0;
        dto.setPlacesDisponibles(
                trajet.getNombrePlaces() - reservations);

        // Infos du conducteur
        if (trajet.getConducteur() != null) {
            dto.setConducteurNom(
                    trajet.getConducteur().getNom());
            dto.setConducteurPrenom(
                    trajet.getConducteur().getPrenom());
        }

        // Infos du véhicule
        if (trajet.getVehicule() != null) {
            dto.setVehiculeMarque(
                    trajet.getVehicule().getMarque());
            dto.setVehiculeModele(
                    trajet.getVehicule().getModele());
            dto.setVehiculeImmatriculation(
                    trajet.getVehicule()
                            .getImmatriculation());
        }

        return dto;
    }

    // Convertit un TripRequestDTO
    // en Trajet (Model)
    public Trajet toModel(TripRequestDTO dto) {

        if (dto == null) return null;

        Trajet trajet = new Trajet();
        trajet.setLieuDepart(dto.getLieuDepart());
        trajet.setLieuArrivee(dto.getLieuArrivee());
        trajet.setDateHeureDepart(
                dto.getDateHeureDepart());
        trajet.setNombrePlaces(
                dto.getNombrePlaces());
        trajet.setDistanceKm(dto.getDistanceKm());

        // Calcul CO2 à la création du trajet
        // Formule : distanceKm * 0.21 kg CO2/km
        trajet.setEconomieCo2Kg(
                dto.getDistanceKm() * 0.21);

        return trajet;
    }
}
