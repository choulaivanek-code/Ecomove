package com.example.ecomove.dto.request;

import com.example.ecomove.enums.MessageType;
import jakarta.validation.constraints.*;

public class MessageRequestDTO {

    @NotBlank(message = "Le contenu est obligatoire")
    @Size(max = 500, message = "Maximum 500 caractères")
    private String contenu;

    @NotNull(message = "Le destinataire est obligatoire")
    private Long destinataireId;

    private Long trajetId;

    @NotNull(message = "Le type est obligatoire")
    private MessageType type;

    // GETTERS
    public String getContenu() { return contenu; }
    public Long getDestinataireId() { return destinataireId; }
    public Long getTrajetId() { return trajetId; }
    public MessageType getType() { return type; }

    // SETTERS
    public void setContenu(String contenu) { this.contenu = contenu; }
    public void setDestinataireId(Long destinataireId) { this.destinataireId = destinataireId; }
    public void setTrajetId(Long trajetId) { this.trajetId = trajetId; }
    public void setType(MessageType type) { this.type = type; }
}