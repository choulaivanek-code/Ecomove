package com.example.ecomove.dto.request;

import jakarta.validation.constraints.*;

public class AuthRequestDTO {

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String motDePasse;

    // GETTERS
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }

    // SETTERS
    public void setEmail(String email) { this.email = email; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
}