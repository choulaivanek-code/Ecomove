package com.example.ecomove.entity;

import com.example.ecomove.enums.MessageType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String contenu;

    @Column(nullable = false)
    private LocalDateTime dateEnvoi
            = LocalDateTime.now();

    @Column(nullable = false)
    private boolean lu = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expediteur_id",
            nullable = false)
    private Utilisateur expediteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinataire_id",
            nullable = false)
    private Utilisateur destinataire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;

    // ================================
    // CONSTRUCTEURS
    // ================================
    public Message() {}

    // ================================
    // GETTERS
    // ================================
    public Long getId() { return id; }
    public String getContenu() { return contenu; }
    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }
    public boolean isLu() { return lu; }
    public MessageType getType() { return type; }
    public Utilisateur getExpediteur() {
        return expediteur;
    }
    public Utilisateur getDestinataire() {
        return destinataire;
    }
    public Trajet getTrajet() { return trajet; }

    // ================================
    // SETTERS
    // ================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    public void setDateEnvoi(
            LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }
    public void setLu(boolean lu) {
        this.lu = lu;
    }
    public void setType(MessageType type) {
        this.type = type;
    }
    public void setExpediteur(
            Utilisateur expediteur) {
        this.expediteur = expediteur;
    }
    public void setDestinataire(
            Utilisateur destinataire) {
        this.destinataire = destinataire;
    }
    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }
}