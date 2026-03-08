package com.example.ecomove.dto.request;

import jakarta.validation.constraints.*;

public class CompanyRequestDTO {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format email invalide")
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    // GETTERS
    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }

    // SETTERS
    public void setNom(String nom) { this.nom = nom; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
}