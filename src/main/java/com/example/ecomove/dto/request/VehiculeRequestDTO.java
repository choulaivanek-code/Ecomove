package com.example.ecomove.dto.request;

import jakarta.validation.constraints.*;

public class VehiculeRequestDTO {

    @NotBlank(message = "La marque est obligatoire")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;

    @NotBlank(message = "L'immatriculation est obligatoire")
    private String immatriculation;

    @NotNull(message = "Le nombre de places est obligatoire")
    @Min(value = 1, message = "Minimum 1 place")
    @Max(value = 9, message = "Maximum 9 places")
    private Integer nombrePlaces;

    // GETTERS
    public String getMarque() { return marque; }
    public String getModele() { return modele; }
    public String getImmatriculation() { return immatriculation; }
    public Integer getNombrePlaces() { return nombrePlaces; }

    // SETTERS
    public void setMarque(String marque) { this.marque = marque; }
    public void setModele(String modele) { this.modele = modele; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
    public void setNombrePlaces(Integer nombrePlaces) { this.nombrePlaces = nombrePlaces; }
}