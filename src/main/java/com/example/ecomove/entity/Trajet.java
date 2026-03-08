package com.example.ecomove.entity;

import com.example.ecomove.enums.TrajetStatut;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trajets")
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lieuDepart;

    @Column(nullable = false)
    private String lieuArrivee;

    @Column(nullable = false)
    private LocalDateTime dateHeureDepart;

    @Column(nullable = false)
    private Integer nombrePlaces;

    @Column(nullable = false)
    private Double distanceKm;

    // Économie CO2 calculée à la création
    // Formule : distanceKm * 0.21 kg CO2/km
    @Column(nullable = false)
    private Double economieCo2Kg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrajetStatut statut
            = TrajetStatut.OUVERT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conducteur_id",
            nullable = false)
    private Utilisateur conducteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    @OneToMany(mappedBy = "trajet",
            cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    // ================================
    // CONSTRUCTEURS
    // ================================
    public Trajet() {}

    // ================================
    // GETTERS
    // ================================
    public Long getId() { return id; }
    public String getLieuDepart() {
        return lieuDepart;
    }
    public String getLieuArrivee() {
        return lieuArrivee;
    }
    public LocalDateTime getDateHeureDepart() {
        return dateHeureDepart;
    }
    public Integer getNombrePlaces() {
        return nombrePlaces;
    }
    public Double getDistanceKm() {
        return distanceKm;
    }
    public Double getEconomieCo2Kg() {
        return economieCo2Kg;
    }
    public TrajetStatut getStatut() {
        return statut;
    }
    public Utilisateur getConducteur() {
        return conducteur;
    }
    public Vehicule getVehicule() {
        return vehicule;
    }
    public List<Reservation> getReservations() {
        return reservations;
    }

    // ================================
    // SETTERS
    // ================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }
    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }
    public void setDateHeureDepart(
            LocalDateTime dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }
    public void setNombrePlaces(
            Integer nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }
    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }
    public void setEconomieCo2Kg(
            Double economieCo2Kg) {
        this.economieCo2Kg = economieCo2Kg;
    }
    public void setStatut(TrajetStatut statut) {
        this.statut = statut;
    }
    public void setConducteur(
            Utilisateur conducteur) {
        this.conducteur = conducteur;
    }
    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }
    public void setReservations(
            List<Reservation> reservations) {
        this.reservations = reservations;
    }
}