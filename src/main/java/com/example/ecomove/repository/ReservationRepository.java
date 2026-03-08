package com.example.ecomove.repository;

import com.example.ecomove.enums.ReservationStatut;
import com.example.ecomove.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository
        extends JpaRepository<Reservation, Long> {

    // Récupérer toutes les réservations
    // d'un passager
    List<Reservation> findByPassagerId(
            Long passagerId);

    // Récupérer toutes les réservations
    // d'un trajet
    List<Reservation> findByTrajetId(
            Long trajetId);

    // Vérifier si un passager est déjà
    // inscrit à un trajet
    // Règle métier RM05
    Optional<Reservation> findByPassagerIdAndTrajetId(
            Long passagerId, Long trajetId);

    // Compter le nombre de réservations
    // pour un trajet
    // Utilisé pour vérifier les places
    // disponibles - Règle métier RM03
    long countByTrajetId(Long trajetId);

    // Récupérer les réservations
    // d'un passager par statut
    List<Reservation> findByPassagerIdAndStatut(
            Long passagerId,
            ReservationStatut statut);
}
