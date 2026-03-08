package com.example.ecomove.repository;

import com.example.ecomove.enums.Role;
import com.example.ecomove.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository
        extends JpaRepository<Utilisateur, Long> {

    // Chercher un utilisateur par email
    // Utilisé pour l'authentification JWT
    Optional<Utilisateur> findByEmail(String email);

    // Vérifier si un email existe déjà
    // Utilisé lors de l'inscription
    boolean existsByEmail(String email);

    // Récupérer tous les utilisateurs
    // d'une entreprise
    List<Utilisateur> findByCompanyId(Long companyId);

    // Récupérer tous les utilisateurs
    // par rôle
    List<Utilisateur> findByRole(Role role);

    // Récupérer les utilisateurs actifs
    // d'une entreprise
    List<Utilisateur> findByCompanyIdAndActif(
            Long companyId, boolean actif);
}
