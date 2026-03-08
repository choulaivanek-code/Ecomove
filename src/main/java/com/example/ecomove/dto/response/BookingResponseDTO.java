package com.example.ecomove.dto.response;

import com.example.ecomove.enums.ReservationStatut;
import java.time.LocalDateTime;

public class BookingResponseDTO {

    private Long id;
    private Long userId;
    private String passagerNom;
    private String passagerPrenom;
    private Long tripId;
    private String lieuDepart;
    private String lieuArrivee;
    private LocalDateTime dateHeureDepart;
    private LocalDateTime dateReservation;
    private ReservationStatut statut;
    private Double economieCo2Kg;
    private String message;

    // GETTERS
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getPassagerNom() { return passagerNom; }
    public String getPassagerPrenom() { return passagerPrenom; }
    public Long getTripId() { return tripId; }
    public String getLieuDepart() { return lieuDepart; }
    public String getLieuArrivee() { return lieuArrivee; }
    public LocalDateTime getDateHeureDepart() { return dateHeureDepart; }
    public LocalDateTime getDateReservation() { return dateReservation; }
    public ReservationStatut getStatut() { return statut; }
    public Double getEconomieCo2Kg() { return economieCo2Kg; }
    public String getMessage() { return message; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setPassagerNom(String passagerNom) { this.passagerNom = passagerNom; }
    public void setPassagerPrenom(String passagerPrenom) { this.passagerPrenom = passagerPrenom; }
    public void setTripId(Long tripId) { this.tripId = tripId; }
    public void setLieuDepart(String lieuDepart) { this.lieuDepart = lieuDepart; }
    public void setLieuArrivee(String lieuArrivee) { this.lieuArrivee = lieuArrivee; }
    public void setDateHeureDepart(LocalDateTime dateHeureDepart) { this.dateHeureDepart = dateHeureDepart; }
    public void setDateReservation(LocalDateTime dateReservation) { this.dateReservation = dateReservation; }
    public void setStatut(ReservationStatut statut) { this.statut = statut; }
    public void setEconomieCo2Kg(Double economieCo2Kg) { this.economieCo2Kg = economieCo2Kg; }
    public void setMessage(String message) { this.message = message; }
}