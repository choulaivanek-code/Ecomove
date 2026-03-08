package com.example.ecomove.mapper;

import com.example.ecomove.dto.request.MessageRequestDTO;
import com.example.ecomove.dto.response.MessageResponseDTO;
import com.example.ecomove.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    // Convertit un Message (Model)
    // en MessageResponseDTO
    public MessageResponseDTO toResponseDTO(
            Message message) {

        if (message == null) return null;

        MessageResponseDTO dto =
                new MessageResponseDTO();

        dto.setId(message.getId());
        dto.setContenu(message.getContenu());
        dto.setDateEnvoi(message.getDateEnvoi());
        dto.setLu(message.isLu());
        dto.setType(message.getType());

        // Infos de l'expéditeur
        if (message.getExpediteur() != null) {
            dto.setExpediteurId(
                    message.getExpediteur().getId());
            dto.setExpediteurNom(
                    message.getExpediteur().getNom());
            dto.setExpediteurPrenom(
                    message.getExpediteur().getPrenom());
        }

        // Infos du destinataire
        if (message.getDestinataire() != null) {
            dto.setDestinataireId(
                    message.getDestinataire().getId());
            dto.setDestinataireNom(
                    message.getDestinataire().getNom());
            dto.setDestinatairePrenom(
                    message.getDestinataire()
                            .getPrenom());
        }

        // Trajet concerné (optionnel)
        if (message.getTrajet() != null) {
            dto.setTrajetId(
                    message.getTrajet().getId());
        }

        return dto;
    }

    // Convertit un MessageRequestDTO
    // en Message (Model)
    public Message toModel(
            MessageRequestDTO dto) {

        if (dto == null) return null;

        Message message = new Message();
        message.setContenu(dto.getContenu());
        message.setType(dto.getType());

        return message;
    }
}
