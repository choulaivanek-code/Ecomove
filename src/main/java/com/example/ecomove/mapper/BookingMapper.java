package com.example.ecomove.mapper;

import com.example.ecomove.dto.response.BookingResponseDTO;
import com.example.ecomove.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    // Convertit une Reservation (Model)
    // en BookingResponseDTO
    public BookingResponseDTO toResponseDTO(
            Reservation reservation) {

        if (reservation == null) return null;

        BookingResponseDTO dto =
                new BookingResponseDTO();

        dto.setId(reservation.getId());
        dto.setDateReservation(
                reservation.getDateReservation());
        dto.setStatut(reservation.getStatut());
        dto.setEconomieCo2Kg(
                reservation.getEconomieCo2Kg());
        dto.setMessage("Réservation créée avec succès");

        // Infos du passager
        if (reservation.getPassager() != null) {
            dto.setUserId(
                    reservation.getPassager().getId());
            dto.setPassagerNom(
                    reservation.getPassager().getNom());
            dto.setPassagerPrenom(
                    reservation.getPassager().getPrenom());
        }

        // Infos du trajet
        if (reservation.getTrajet() != null) {
            dto.setTripId(
                    reservation.getTrajet().getId());
            dto.setLieuDepart(
                    reservation.getTrajet()
                            .getLieuDepart());
            dto.setLieuArrivee(
                    reservation.getTrajet()
                            .getLieuArrivee());
            dto.setDateHeureDepart(
                    reservation.getTrajet()
                            .getDateHeureDepart());
        }

        return dto;
    }
}
