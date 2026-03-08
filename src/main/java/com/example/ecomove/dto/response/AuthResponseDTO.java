package com.example.ecomove.dto.response;

import com.example.ecomove.enums.Role;

public class AuthResponseDTO {

    private String token;
    private String tokenType = "Bearer";
    private Long userId;
    private String email;
    private String nom;
    private String prenom;
    private Role role;

    // GETTERS
    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public Role getRole() { return role; }

    // SETTERS
    public void setToken(String token) { this.token = token; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setEmail(String email) { this.email = email; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setRole(Role role) { this.role = role; }
}