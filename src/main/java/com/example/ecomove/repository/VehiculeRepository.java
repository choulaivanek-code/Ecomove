package com.example.ecomove.repository;

import com.example.ecomove.enums.VehiculeEtat;
import com.example.ecomove.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculeRepository
        extends JpaRepository<Vehicule, Long> {

    // Récupérer tous les véhicules
    // d'un propriétaire
    List<Vehicule> findByProprietaireId(
            Long proprietaireId);

    // Vérifier si une immatriculation
    // existe déjà
    boolean existsByImmatriculation(
            String immatriculation);

    // Récupérer un véhicule par
    // immatriculation
    Optional<Vehicule> findByImmatriculation(
            String immatriculation);

    // Récupérer les véhicules disponibles
    // d'un propriétaire
    List<Vehicule> findByProprietaireIdAndEtat(
            Long proprietaireId,
            VehiculeEtat etat);
}
