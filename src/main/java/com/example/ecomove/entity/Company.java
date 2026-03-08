package com.example.ecomove.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private boolean actif = true;

    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL)
    private List<Utilisateur> employes;

    public Company() {}

    public Company(Long id, String nom,
                   String adresse, String email,
                   String telephone, boolean actif) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.actif = actif;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public boolean isActif() { return actif; }
    public List<Utilisateur> getEmployes() {
        return employes;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setActif(boolean actif) {
        this.actif = actif;
    }
    public void setEmployes(
            List<Utilisateur> employes) {
        this.employes = employes;
    }
}