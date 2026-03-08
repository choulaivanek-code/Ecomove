package com.example.ecomove.controller;

import com.example.ecomove.dto.request.CompanyRequestDTO;
import com.example.ecomove.dto.response.CompanyResponseDTO;
import com.example.ecomove.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // POST /api/v1/companies
    // Créer une entreprise partenaire
    @PostMapping
    public ResponseEntity<CompanyResponseDTO>
    creer(
            @Valid @RequestBody
            CompanyRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.creer(dto));
    }

    // GET /api/v1/companies
    // Récupérer toutes les entreprises
    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>>
    recupererToutes() {

        return ResponseEntity.ok(
                companyService.recupererToutes());
    }

    // GET /api/v1/companies/{id}
    // Récupérer une entreprise par id
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO>
    recupererParId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                companyService.recupererParId(id));
    }

    // PUT /api/v1/companies/{id}
    // Modifier une entreprise
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO>
    modifier(
            @PathVariable Long id,
            @Valid @RequestBody
            CompanyRequestDTO dto) {

        return ResponseEntity.ok(
                companyService.modifier(id, dto));
    }

    // DELETE /api/v1/companies/{id}
    // Désactiver une entreprise
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactiver(
            @PathVariable Long id) {

        companyService.desactiver(id);
        return ResponseEntity
                .noContent().build();
    }
}
