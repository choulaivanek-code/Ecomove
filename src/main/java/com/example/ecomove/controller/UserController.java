package com.example.ecomove.controller;

import com.example.ecomove.dto.request.UserRequestDTO;
import com.example.ecomove.dto.response.UserResponseDTO;
import com.example.ecomove.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET /api/v1/users
    // Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>>
    recupererTous() {

        return ResponseEntity
                .ok(userService.recupererTous());
    }

    // GET /api/v1/users/{id}
    // Récupérer un utilisateur par id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO>
    recupererParId(
            @PathVariable Long id) {

        return ResponseEntity
                .ok(userService.recupererParId(id));
    }

    // PUT /api/v1/users/{id}
    // Modifier un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO>
    modifier(
            @PathVariable Long id,
            @Valid @RequestBody
            UserRequestDTO dto) {

        return ResponseEntity
                .ok(userService.modifier(id, dto));
    }

    // DELETE /api/v1/users/{id}
    // Désactiver un utilisateur
    // On ne supprime jamais en BDD !
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactiver(
            @PathVariable Long id) {

        userService.desactiver(id);
        return ResponseEntity
                .noContent().build();
    }

    // GET /api/v1/users/company/{companyId}
    // Récupérer les employés d'une entreprise
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<UserResponseDTO>>
    recupererParCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                userService
                        .recupererParCompany(companyId));
    }
}
