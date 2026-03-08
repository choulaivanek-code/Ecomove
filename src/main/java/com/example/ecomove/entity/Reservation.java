package com.example.ecomove.entity;

import com.example.ecomove.enums.ReservationStatut;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passager_id",
            nullable = false)
    private Utilisateur passager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trajet_id",
            nullable = false)
    private Trajet trajet;

    @Column(nullable = false)
    private LocalDateTime dateReservation
            = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatut statut
            = ReservationStatut.EN_ATTENTE;

    // Économie CO2 : distanceKm * 0.21
    @Column(nullable = false)
    private Double economieCo2Kg;

    // ================================
    // CONSTRUCTEURS
    // ================================
    public Reservation() {}

    // ================================
    // GETTERS
    // ================================
    public Long getId() { return id; }
    public Utilisateur getPassager() {
        return passager;
    }
    public Trajet getTrajet() { return trajet; }
    public LocalDateTime getDateReservation() {
        return dateReservation;
    }
    public ReservationStatut getStatut() {
        return statut;
    }
    public Double getEconomieCo2Kg() {
        return economieCo2Kg;
    }

    // ================================
    // SETTERS
    // ================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setPassager(Utilisateur passager) {
        this.passager = passager;
    }
    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }
    public void setDateReservation(
            LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }
    public void setStatut(
            ReservationStatut statut) {
        this.statut = statut;
    }
    public void setEconomieCo2Kg(
            Double economieCo2Kg) {
        this.economieCo2Kg = economieCo2Kg;
    }
}