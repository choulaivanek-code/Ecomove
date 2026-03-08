package com.example.ecomove.service;

import com.example.ecomove.dto.request.MessageRequestDTO;
import com.example.ecomove.dto.response.MessageResponseDTO;
import com.example.ecomove.exception.ResourceNotFoundException;
import com.example.ecomove.mapper.MessageMapper;
import com.example.ecomove.entity.Message;
import com.example.ecomove.entity.Trajet;
import com.example.ecomove.entity.Utilisateur;
import com.example.ecomove.repository.MessageRepository;
import com.example.ecomove.repository.TrajetRepository;
import com.example.ecomove.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private TrajetRepository trajetRepository;

    @Autowired
    private MessageMapper messageMapper;

    // Envoyer un message
    public MessageResponseDTO envoyer(
            Long expediteurId,
            MessageRequestDTO dto) {

        // Vérifier que l'expéditeur existe
        Utilisateur expediteur =
                utilisateurRepository
                        .findById(expediteurId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Utilisateur",
                                        expediteurId));

        // Vérifier que le destinataire existe
        Utilisateur destinataire =
                utilisateurRepository
                        .findById(dto.getDestinataireId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Utilisateur",
                                        dto.getDestinataireId()));

        // Convertir DTO en Model
        Message message =
                messageMapper.toModel(dto);
        message.setExpediteur(expediteur);
        message.setDestinataire(destinataire);

        // Associer le trajet si fourni
        if (dto.getTrajetId() != null) {
            Trajet trajet = trajetRepository
                    .findById(dto.getTrajetId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Trajet",
                                    dto.getTrajetId()));
            message.setTrajet(trajet);
        }

        return messageMapper.toResponseDTO(
                messageRepository.save(message));
    }

    // Récupérer les messages reçus
    public List<MessageResponseDTO>
    recupererMessagesRecus(
            Long utilisateurId) {

        return messageRepository
                .findByDestinataireId(utilisateurId)
                .stream()
                .map(messageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Récupérer les messages non lus
    public List<MessageResponseDTO>
    recupererNonLus(Long utilisateurId) {

        return messageRepository
                .findByDestinataireIdAndLu(
                        utilisateurId, false)
                .stream()
                .map(messageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Marquer un message comme lu
    public MessageResponseDTO marquerCommeLu(
            Long id) {

        Message message = messageRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Message", id));

        message.setLu(true);

        return messageMapper.toResponseDTO(
                messageRepository.save(message));
    }

    // Compter les messages non lus
    public long compterNonLus(
            Long utilisateurId) {

        return messageRepository
                .countByDestinataireIdAndLu(
                        utilisateurId, false);
    }
}
