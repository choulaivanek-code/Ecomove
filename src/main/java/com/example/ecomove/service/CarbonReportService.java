package com.example.ecomove.service;

import com.example.ecomove.dto.response.CarbonReportResponseDTO;
import com.example.ecomove.exception.ResourceNotFoundException;
import com.example.ecomove.entity.Reservation;
import com.example.ecomove.entity.Utilisateur;
import com.example.ecomove.repository.ReservationRepository;
import com.example.ecomove.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarbonReportService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Générer un rapport CO2
    // pour un utilisateur
    public CarbonReportResponseDTO
    rapportParUtilisateur(Long userId) {

        Utilisateur utilisateur =
                utilisateurRepository
                        .findById(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Utilisateur", userId));

        List<Reservation> reservations =
                reservationRepository
                        .findByPassagerId(userId);

        // Calcul des totaux
        double totalCo2 = reservations.stream()
                .mapToDouble(
                        Reservation::getEconomieCo2Kg)
                .sum();

        double totalKm = reservations.stream()
                .mapToDouble(r ->
                        r.getTrajet().getDistanceKm())
                .sum();

        // Construire le rapport
        CarbonReportResponseDTO rapport =
                new CarbonReportResponseDTO();

        rapport.setUserId(userId);
        rapport.setNomUtilisateur(
                utilisateur.getNom() + " "
                        + utilisateur.getPrenom());
        rapport.setTotalEconomieCo2Kg(totalCo2);

        // 1 arbre absorbe ~22 kg CO2/an
        rapport.setEquivalentArbres(
                totalCo2 / 22.0);

        rapport.setNombreTrajets(
                reservations.size());
        rapport.setTotalKilometres(totalKm);
        rapport.setPeriode("Total depuis "
                + "l'inscription");

        // Nom de l'entreprise si disponible
        if (utilisateur.getCompany() != null) {
            rapport.setNomCompany(
                    utilisateur.getCompany()
                            .getNom());
        }

        return rapport;
    }

    // Générer un rapport CO2
    // pour une entreprise entière
    public CarbonReportResponseDTO
    rapportParCompany(Long companyId) {

        List<Utilisateur> employes =
                utilisateurRepository
                        .findByCompanyId(companyId);

        // Additionner les CO2 de tous
        // les employés
        double totalCo2 = employes.stream()
                .flatMap(e ->
                        reservationRepository
                                .findByPassagerId(e.getId())
                                .stream())
                .mapToDouble(
                        Reservation::getEconomieCo2Kg)
                .sum();

        int totalTrajets = employes.stream()
                .mapToInt(e ->
                        reservationRepository
                                .findByPassagerId(e.getId())
                                .size())
                .sum();

        CarbonReportResponseDTO rapport =
                new CarbonReportResponseDTO();

        rapport.setTotalEconomieCo2Kg(totalCo2);
        rapport.setEquivalentArbres(
                totalCo2 / 22.0);
        rapport.setNombreTrajets(totalTrajets);
        rapport.setPeriode(
                "Total de l'entreprise");

        return rapport;
    }
}
