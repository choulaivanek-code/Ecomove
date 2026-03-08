package com.example.ecomove.service;

import com.example.ecomove.dto.response.BookingResponseDTO;
import com.example.ecomove.enums.ReservationStatut;
import com.example.ecomove.enums.TrajetStatut;
import com.example.ecomove.exception.ReservationException;
import com.example.ecomove.exception.ResourceNotFoundException;
import com.example.ecomove.mapper.BookingMapper;
import com.example.ecomove.entity.Reservation;
import com.example.ecomove.entity.Trajet;
import com.example.ecomove.entity.Utilisateur;
import com.example.ecomove.repository.ReservationRepository;
import com.example.ecomove.repository.TrajetRepository;
import com.example.ecomove.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TrajetRepository trajetRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private BookingMapper bookingMapper;

    // Créer une réservation
    // Applique les 5 règles métier de l'épreuve
    public BookingResponseDTO creerReservation(
            Long userId, Long tripId) {

        // Vérifier que l'utilisateur existe
        Utilisateur passager =
                utilisateurRepository
                        .findById(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Utilisateur", userId));

        // RM01 : Le trajet doit exister
        Trajet trajet = trajetRepository
                .findById(tripId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Trajet", tripId));

        // RM02 : Le trajet doit être ouvert
        if (trajet.getStatut()
                != TrajetStatut.OUVERT) {
            throw new ReservationException(
                    "Le trajet n'est pas disponible. "
                            + "Statut actuel : "
                            + trajet.getStatut());
        }

        // RM03 : Des places doivent être
        // disponibles
        long nbReservations =
                reservationRepository
                        .countByTrajetId(tripId);

        if (nbReservations
                >= trajet.getNombrePlaces()) {
            throw new ReservationException(
                    "Le trajet est complet. "
                            + "Plus de places disponibles");
        }

        // RM04 : Un conducteur ne peut pas
        // réserver son propre trajet
        if (trajet.getConducteur().getId()
                .equals(userId)) {
            throw new ReservationException(
                    "Vous ne pouvez pas réserver "
                            + "votre propre trajet");
        }

        // RM05 : Un passager ne peut pas
        // réserver deux fois le même trajet
        reservationRepository
                .findByPassagerIdAndTrajetId(
                        userId, tripId)
                .ifPresent(r -> {
                    throw new ReservationException(
                            "Vous êtes déjà inscrit "
                                    + "à ce trajet");
                });

        // Créer la réservation
        Reservation reservation =
                new Reservation();
        reservation.setPassager(passager);
        reservation.setTrajet(trajet);
        reservation.setStatut(
                ReservationStatut.CONFIRMEE);

        // Calcul économie CO2
        // distanceKm * 0.21 kg CO2/km
        reservation.setEconomieCo2Kg(
                trajet.getDistanceKm() * 0.21);

        // Mettre le trajet à COMPLET si
        // c'est la dernière place
        if (nbReservations + 1
                >= trajet.getNombrePlaces()) {
            trajet.setStatut(TrajetStatut.COMPLET);
            trajetRepository.save(trajet);
        }

        return bookingMapper.toResponseDTO(
                reservationRepository
                        .save(reservation));
    }

    // Récupérer les réservations d'un passager
    public List<BookingResponseDTO>
    recupererParPassager(Long passagerId) {

        return reservationRepository
                .findByPassagerId(passagerId)
                .stream()
                .map(bookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Annuler une réservation
    public BookingResponseDTO annuler(Long id) {

        Reservation reservation =
                reservationRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Reservation", id));

        reservation.setStatut(
                ReservationStatut.ANNULEE);

        // Remettre le trajet à OUVERT
        // si il était COMPLET
        Trajet trajet = reservation.getTrajet();
        if (trajet.getStatut()
                == TrajetStatut.COMPLET) {
            trajet.setStatut(TrajetStatut.OUVERT);
            trajetRepository.save(trajet);
        }

        return bookingMapper.toResponseDTO(
                reservationRepository
                        .save(reservation));
    }
}
