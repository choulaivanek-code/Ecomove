package com.example.ecomove.entity;

import com.example.ecomove.enums.Role;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    @Column(nullable = false)
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean actif = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "conducteur",
            cascade = CascadeType.ALL)
    private List<Trajet> trajetsProposes;

    @OneToMany(mappedBy = "passager",
            cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "proprietaire",
            cascade = CascadeType.ALL)
    private List<Vehicule> vehicules;

    @OneToMany(mappedBy = "expediteur",
            cascade = CascadeType.ALL)
    private List<Message> messagesEnvoyes;

    // ================================
    // CONSTRUCTEURS
    // ================================
    public Utilisateur() {}

    // ================================
    // GETTERS
    // ================================
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
    public String getTelephone() { return telephone; }
    public Role getRole() { return role; }
    public boolean isActif() { return actif; }
    public Company getCompany() { return company; }
    public List<Trajet> getTrajetsProposes() {
        return trajetsProposes;
    }
    public List<Reservation> getReservations() {
        return reservations;
    }
    public List<Vehicule> getVehicules() {
        return vehicules;
    }
    public List<Message> getMessagesEnvoyes() {
        return messagesEnvoyes;
    }

    // ================================
    // SETTERS
    // ================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setActif(boolean actif) {
        this.actif = actif;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public void setTrajetsProposes(
            List<Trajet> trajetsProposes) {
        this.trajetsProposes = trajetsProposes;
    }
    public void setReservations(
            List<Reservation> reservations) {
        this.reservations = reservations;
    }
    public void setVehicules(
            List<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }
    public void setMessagesEnvoyes(
            List<Message> messagesEnvoyes) {
        this.messagesEnvoyes = messagesEnvoyes;
    }
}