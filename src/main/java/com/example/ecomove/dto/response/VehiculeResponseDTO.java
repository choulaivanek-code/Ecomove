package com.example.ecomove.dto.response;

import com.example.ecomove.enums.VehiculeEtat;

public class VehiculeResponseDTO {

    private Long id;
    private String marque;
    private String modele;
    private String immatriculation;
    private Integer nombrePlaces;
    private VehiculeEtat etat;
    private String proprietaireNom;
    private String proprietairePrenom;

    // GETTERS
    public Long getId() { return id; }
    public String getMarque() { return marque; }
    public String getModele() { return modele; }
    public String getImmatriculation() { return immatriculation; }
    public Integer getNombrePlaces() { return nombrePlaces; }
    public VehiculeEtat getEtat() { return etat; }
    public String getProprietaireNom() { return proprietaireNom; }
    public String getProprietairePrenom() { return proprietairePrenom; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setMarque(String marque) { this.marque = marque; }
    public void setModele(String modele) { this.modele = modele; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
    public void setNombrePlaces(Integer nombrePlaces) { this.nombrePlaces = nombrePlaces; }
    public void setEtat(VehiculeEtat etat) { this.etat = etat; }
    public void setProprietaireNom(String proprietaireNom) { this.proprietaireNom = proprietaireNom; }
    public void setProprietairePrenom(String proprietairePrenom) { this.proprietairePrenom = proprietairePrenom; }
}