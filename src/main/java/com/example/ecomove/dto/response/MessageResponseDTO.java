package com.example.ecomove.dto.response;

import com.example.ecomove.enums.MessageType;
import java.time.LocalDateTime;

public class MessageResponseDTO {

    private Long id;
    private String contenu;
    private LocalDateTime dateEnvoi;
    private boolean lu;
    private MessageType type;
    private Long expediteurId;
    private String expediteurNom;
    private String expediteurPrenom;
    private Long destinataireId;
    private String destinataireNom;
    private String destinatairePrenom;
    private Long trajetId;

    // GETTERS
    public Long getId() { return id; }
    public String getContenu() { return contenu; }
    public LocalDateTime getDateEnvoi() { return dateEnvoi; }
    public boolean isLu() { return lu; }
    public MessageType getType() { return type; }
    public Long getExpediteurId() { return expediteurId; }
    public String getExpediteurNom() { return expediteurNom; }
    public String getExpediteurPrenom() { return expediteurPrenom; }
    public Long getDestinataireId() { return destinataireId; }
    public String getDestinataireNom() { return destinataireNom; }
    public String getDestinatairePrenom() { return destinatairePrenom; }
    public Long getTrajetId() { return trajetId; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public void setDateEnvoi(LocalDateTime dateEnvoi) { this.dateEnvoi = dateEnvoi; }
    public void setLu(boolean lu) { this.lu = lu; }
    public void setType(MessageType type) { this.type = type; }
    public void setExpediteurId(Long expediteurId) { this.expediteurId = expediteurId; }
    public void setExpediteurNom(String expediteurNom) { this.expediteurNom = expediteurNom; }
    public void setExpediteurPrenom(String expediteurPrenom) { this.expediteurPrenom = expediteurPrenom; }
    public void setDestinataireId(Long destinataireId) { this.destinataireId = destinataireId; }
    public void setDestinataireNom(String destinataireNom) { this.destinataireNom = destinataireNom; }
    public void setDestinatairePrenom(String destinatairePrenom) { this.destinatairePrenom = destinatairePrenom; }
    public void setTrajetId(Long trajetId) { this.trajetId = trajetId; }
}