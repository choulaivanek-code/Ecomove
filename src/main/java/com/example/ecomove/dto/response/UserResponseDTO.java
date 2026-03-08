package com.example.ecomove.dto.response;

import com.example.ecomove.enums.Role;

public class UserResponseDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Role role;
    private boolean actif;
    private String companyNom;

    // GETTERS
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public Role getRole() { return role; }
    public boolean isActif() { return actif; }
    public String getCompanyNom() { return companyNom; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setRole(Role role) { this.role = role; }
    public void setActif(boolean actif) { this.actif = actif; }
    public void setCompanyNom(String companyNom) { this.companyNom = companyNom; }
}