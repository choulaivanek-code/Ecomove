package com.example.ecomove.controller;

import com.example.ecomove.dto.request.TripRequestDTO;
import com.example.ecomove.dto.response.TripResponseDTO;
import com.example.ecomove.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    // POST /api/v1/trips?conducteurId={id}
    // Créer un nouveau trajet
    @PostMapping
    public ResponseEntity<TripResponseDTO>
    creer(
            @RequestParam Long conducteurId,
            @Valid @RequestBody
            TripRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tripService.creer(
                        conducteurId, dto));
    }

    // GET /api/v1/trips
    // Récupérer tous les trajets ouverts
    @GetMapping
    public ResponseEntity<List<TripResponseDTO>>
    recupererTrajetsOuverts() {

        return ResponseEntity.ok(
                tripService
                        .recupererTrajetsOuverts());
    }

    // GET /api/v1/trips/{id}
    // Récupérer un trajet par id
    @GetMapping("/{id}")
    public ResponseEntity<TripResponseDTO>
    recupererParId(
            @PathVariable Long id) {

        return ResponseEntity
                .ok(tripService.recupererParId(id));
    }

    // GET /api/v1/trips/search
    // Rechercher des trajets disponibles
    @GetMapping("/search")
    public ResponseEntity<List<TripResponseDTO>>
    rechercher(
            @RequestParam String lieuDepart,
            @RequestParam String lieuArrivee,
            @RequestParam
            @DateTimeFormat(
                    iso = DateTimeFormat.ISO
                            .DATE_TIME)
            LocalDateTime date) {

        return ResponseEntity.ok(
                tripService.rechercher(
                        lieuDepart,
                        lieuArrivee,
                        date));
    }

    // GET /api/v1/trips/conducteur/{id}
    // Récupérer les trajets d'un conducteur
    @GetMapping("/conducteur/{conducteurId}")
    public ResponseEntity<List<TripResponseDTO>>
    recupererParConducteur(
            @PathVariable Long conducteurId) {

        return ResponseEntity.ok(
                tripService.recupererParConducteur(
                        conducteurId));
    }

    // PATCH /api/v1/trips/{id}/annuler
    // Annuler un trajet
    @PatchMapping("/{id}/annuler")
    public ResponseEntity<TripResponseDTO>
    annuler(@PathVariable Long id) {

        return ResponseEntity
                .ok(tripService.annuler(id));
    }
}
