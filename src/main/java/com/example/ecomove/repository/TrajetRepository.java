package com.example.ecomove.repository;

import com.example.ecomove.enums.TrajetStatut;
import com.example.ecomove.entity.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrajetRepository
        extends JpaRepository<Trajet, Long> {

    // Récupérer tous les trajets ouverts
    // Utilisé pour la recherche de trajets
    List<Trajet> findByStatut(TrajetStatut statut);

    // Récupérer les trajets d'un conducteur
    List<Trajet> findByConducteurId(
            Long conducteurId);

    // Rechercher des trajets par lieu
    // de départ et d'arrivée
    List<Trajet> findByLieuDepartAndLieuArrivee(
            String lieuDepart, String lieuArrivee);

    // Rechercher des trajets disponibles
    // entre deux lieux et à partir
    // d'une date donnée
    @Query("SELECT t FROM Trajet t " +
            "WHERE t.lieuDepart = :depart " +
            "AND t.lieuArrivee = :arrivee " +
            "AND t.dateHeureDepart >= :date " +
            "AND t.statut = 'OUVERT'")
    List<Trajet> rechercherTrajets(
            @Param("depart") String lieuDepart,
            @Param("arrivee") String lieuArrivee,
            @Param("date") LocalDateTime date);

    // Récupérer les trajets d'un conducteur
    // par statut
    List<Trajet> findByConducteurIdAndStatut(
            Long conducteurId, TrajetStatut statut);
}
