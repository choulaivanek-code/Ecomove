package com.example.ecomove.dto.response;

import com.example.ecomove.enums.TrajetStatut;
import java.time.LocalDateTime;

public class TripResponseDTO {

    private Long id;
    private String lieuDepart;
    private String lieuArrivee;
    private LocalDateTime dateHeureDepart;
    private Integer nombrePlaces;
    private Integer placesDisponibles;
    private Double distanceKm;
    private Double economieCo2Kg;
    private TrajetStatut statut;
    private String conducteurNom;
    private String conducteurPrenom;
    private String vehiculeMarque;
    private String vehiculeModele;
    private String vehiculeImmatriculation;

    // GETTERS
    public Long getId() { return id; }
    public String getLieuDepart() { return lieuDepart; }
    public String getLieuArrivee() { return lieuArrivee; }
    public LocalDateTime getDateHeureDepart() { return dateHeureDepart; }
    public Integer getNombrePlaces() { return nombrePlaces; }
    public Integer getPlacesDisponibles() { return placesDisponibles; }
    public Double getDistanceKm() { return distanceKm; }
    public Double getEconomieCo2Kg() { return economieCo2Kg; }
    public TrajetStatut getStatut() { return statut; }
    public String getConducteurNom() { return conducteurNom; }
    public String getConducteurPrenom() { return conducteurPrenom; }
    public String getVehiculeMarque() { return vehiculeMarque; }
    public String getVehiculeModele() { return vehiculeModele; }
    public String getVehiculeImmatriculation() { return vehiculeImmatriculation; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setLieuDepart(String lieuDepart) { this.lieuDepart = lieuDepart; }
    public void setLieuArrivee(String lieuArrivee) { this.lieuArrivee = lieuArrivee; }
    public void setDateHeureDepart(LocalDateTime dateHeureDepart) { this.dateHeureDepart = dateHeureDepart; }
    public void setNombrePlaces(Integer nombrePlaces) { this.nombrePlaces = nombrePlaces; }
    public void setPlacesDisponibles(Integer placesDisponibles) { this.placesDisponibles = placesDisponibles; }
    public void setDistanceKm(Double distanceKm) { this.distanceKm = distanceKm; }
    public void setEconomieCo2Kg(Double economieCo2Kg) { this.economieCo2Kg = economieCo2Kg; }
    public void setStatut(TrajetStatut statut) { this.statut = statut; }
    public void setConducteurNom(String conducteurNom) { this.conducteurNom = conducteurNom; }
    public void setConducteurPrenom(String conducteurPrenom) { this.conducteurPrenom = conducteurPrenom; }
    public void setVehiculeMarque(String vehiculeMarque) { this.vehiculeMarque = vehiculeMarque; }
    public void setVehiculeModele(String vehiculeModele) { this.vehiculeModele = vehiculeModele; }
    public void setVehiculeImmatriculation(String vehiculeImmatriculation) { this.vehiculeImmatriculation = vehiculeImmatriculation; }
}