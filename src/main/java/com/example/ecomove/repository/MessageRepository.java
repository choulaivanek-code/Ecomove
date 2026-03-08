package com.example.ecomove.repository;

import com.example.ecomove.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository
        extends JpaRepository<Message, Long> {

    // Récupérer tous les messages
    // reçus par un utilisateur
    List<Message> findByDestinataireId(
            Long destinataireId);

    // Récupérer tous les messages
    // envoyés par un utilisateur
    List<Message> findByExpediteurId(
            Long expediteurId);

    // Récupérer les messages non lus
    // d'un utilisateur
    List<Message> findByDestinataireIdAndLu(
            Long destinataireId, boolean lu);

    // Récupérer les messages liés
    // à un trajet
    List<Message> findByTrajetId(Long trajetId);

    // Compter les messages non lus
    // d'un utilisateur
    long countByDestinataireIdAndLu(
            Long destinataireId, boolean lu);
}
