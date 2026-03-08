package com.example.ecomove.controller;

import com.example.ecomove.dto.request.VehiculeRequestDTO;
import com.example.ecomove.dto.response.VehiculeResponseDTO;
import com.example.ecomove.service.VehiculeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicules")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    // POST /api/v1/vehicules?proprietaireId={id}
    // Ajouter un véhicule
    @PostMapping
    public ResponseEntity<VehiculeResponseDTO>
    ajouter(
            @RequestParam Long proprietaireId,
            @Valid @RequestBody
            VehiculeRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vehiculeService.ajouter(
                        proprietaireId, dto));
    }

    // GET /api/v1/vehicules/{id}
    // Récupérer un véhicule par id
    @GetMapping("/{id}")
    public ResponseEntity<VehiculeResponseDTO>
    recupererParId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                vehiculeService.recupererParId(id));
    }

    // GET /api/v1/vehicules/proprietaire/{id}
    // Récupérer les véhicules d'un utilisateur
    @GetMapping("/proprietaire/{proprietaireId}")
    public ResponseEntity<List<VehiculeResponseDTO>>
    recupererParProprietaire(
            @PathVariable
            Long proprietaireId) {

        return ResponseEntity.ok(
                vehiculeService
                        .recupererParProprietaire(
                                proprietaireId));
    }

    // PUT /api/v1/vehicules/{id}
    // Modifier un véhicule
    @PutMapping("/{id}")
    public ResponseEntity<VehiculeResponseDTO>
    modifier(
            @PathVariable Long id,
            @Valid @RequestBody
            VehiculeRequestDTO dto) {

        return ResponseEntity.ok(
                vehiculeService.modifier(id, dto));
    }

    // DELETE /api/v1/vehicules/{id}
    // Supprimer un véhicule
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(
            @PathVariable Long id) {

        vehiculeService.supprimer(id);
        return ResponseEntity
                .noContent().build();
    }
}
