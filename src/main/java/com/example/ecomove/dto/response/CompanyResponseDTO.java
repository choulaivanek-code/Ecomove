package com.example.ecomove.dto.response;

public class CompanyResponseDTO {

    private Long id;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;
    private boolean actif;
    private Integer nombreEmployes;

    // GETTERS
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public boolean isActif() { return actif; }
    public Integer getNombreEmployes() { return nombreEmployes; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setActif(boolean actif) { this.actif = actif; }
    public void setNombreEmployes(Integer nombreEmployes) { this.nombreEmployes = nombreEmployes; }
}