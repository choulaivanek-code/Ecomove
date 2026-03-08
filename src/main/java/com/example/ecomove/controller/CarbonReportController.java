package com.example.ecomove.controller;

import com.example.ecomove.dto.response.CarbonReportResponseDTO;
import com.example.ecomove.service.CarbonReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carbon-reports")
public class CarbonReportController {

    @Autowired
    private CarbonReportService carbonReportService;

    // GET /api/v1/carbon-reports/user/{userId}
    // Rapport CO2 pour un utilisateur
    // Utilisé par l'app mobile
    @GetMapping("/user/{userId}")
    public ResponseEntity<CarbonReportResponseDTO>
    rapportParUtilisateur(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                carbonReportService
                        .rapportParUtilisateur(userId));
    }

    // GET /api/v1/carbon-reports/company/{id}
    // Rapport CO2 pour une entreprise entière
    // Utilisé par le SI des entreprises
    // partenaires
    @GetMapping("/company/{companyId}")
    public ResponseEntity<CarbonReportResponseDTO>
    rapportParCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                carbonReportService
                        .rapportParCompany(companyId));
    }
}
