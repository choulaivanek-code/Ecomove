package com.example.ecomove.dto.request;

import com.example.ecomove.enums.Role;
import jakarta.validation.constraints.*;

public class UserRequestDTO {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Minimum 8 caractères")
    private String motDePasse;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotNull(message = "Le rôle est obligatoire")
    private Role role;

    private Long companyId;

    // GETTERS
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
    public String getTelephone() { return telephone; }
    public Role getRole() { return role; }
    public Long getCompanyId() { return companyId; }

    // SETTERS
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setRole(Role role) { this.role = role; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
}