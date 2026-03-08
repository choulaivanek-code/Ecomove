package com.example.ecomove.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class TripRequestDTO {

    @NotBlank(message = "Le lieu de départ est obligatoire")
    private String lieuDepart;

    @NotBlank(message = "Le lieu d'arrivée est obligatoire")
    private String lieuArrivee;

    @NotNull(message = "La date de départ est obligatoire")
    @Future(message = "La date doit être dans le futur")
    private LocalDateTime dateHeureDepart;

    @NotNull(message = "Le nombre de places est obligatoire")
    @Min(value = 1, message = "Minimum 1 place")
    @Max(value = 8, message = "Maximum 8 places")
    private Integer nombrePlaces;

    @NotNull(message = "La distance est obligatoire")
    @Positive(message = "La distance doit être positive")
    private Double distanceKm;

    @NotNull(message = "Le véhicule est obligatoire")
    private Long vehiculeId;

    // GETTERS
    public String getLieuDepart() { return lieuDepart; }
    public String getLieuArrivee() { return lieuArrivee; }
    public LocalDateTime getDateHeureDepart() { return dateHeureDepart; }
    public Integer getNombrePlaces() { return nombrePlaces; }
    public Double getDistanceKm() { return distanceKm; }
    public Long getVehiculeId() { return vehiculeId; }

    // SETTERS
    public void setLieuDepart(String lieuDepart) { this.lieuDepart = lieuDepart; }
    public void setLieuArrivee(String lieuArrivee) { this.lieuArrivee = lieuArrivee; }
    public void setDateHeureDepart(LocalDateTime dateHeureDepart) { this.dateHeureDepart = dateHeureDepart; }
    public void setNombrePlaces(Integer nombrePlaces) { this.nombrePlaces = nombrePlaces; }
    public void setDistanceKm(Double distanceKm) { this.distanceKm = distanceKm; }
    public void setVehiculeId(Long vehiculeId) { this.vehiculeId = vehiculeId; }
}