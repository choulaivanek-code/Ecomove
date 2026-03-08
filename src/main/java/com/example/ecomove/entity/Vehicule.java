package com.example.ecomove.entity;

import com.example.ecomove.enums.VehiculeEtat;
import jakarta.persistence.*;

@Entity
@Table(name = "vehicules")
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marque;

    @Column(nullable = false)
    private String modele;

    @Column(nullable = false, unique = true)
    private String immatriculation;

    @Column(nullable = false)
    private Integer nombrePlaces;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehiculeEtat etat
            = VehiculeEtat.DISPONIBLE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proprietaire_id",
            nullable = false)
    private Utilisateur proprietaire;

    // ================================
    // CONSTRUCTEURS
    // ================================
    public Vehicule() {}

    // ================================
    // GETTERS
    // ================================
    public Long getId() { return id; }
    public String getMarque() { return marque; }
    public String getModele() { return modele; }
    public String getImmatriculation() {
        return immatriculation;
    }
    public Integer getNombrePlaces() {
        return nombrePlaces;
    }
    public VehiculeEtat getEtat() { return etat; }
    public Utilisateur getProprietaire() {
        return proprietaire;
    }

    // ================================
    // SETTERS
    // ================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
    public void setImmatriculation(
            String immatriculation) {
        this.immatriculation = immatriculation;
    }
    public void setNombrePlaces(
            Integer nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }
    public void setEtat(VehiculeEtat etat) {
        this.etat = etat;
    }
    public void setProprietaire(
            Utilisateur proprietaire) {
        this.proprietaire = proprietaire;
    }
}