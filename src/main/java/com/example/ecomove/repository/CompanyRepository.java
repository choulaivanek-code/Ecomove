package com.example.ecomove.repository;

import com.example.ecomove.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository
        extends JpaRepository<Company, Long> {

    // Vérifier si une entreprise existe
    // par son email
    boolean existsByEmail(String email);

    // Vérifier si une entreprise existe
    // par son nom
    boolean existsByNom(String nom);

    // Récupérer une entreprise par email
    Optional<Company> findByEmail(String email);

    // Récupérer toutes les entreprises
    // actives
    List<Company> findByActif(boolean actif);
}
