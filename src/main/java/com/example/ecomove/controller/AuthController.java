package com.example.ecomove.controller;

import com.example.ecomove.dto.request.AuthRequestDTO;
import com.example.ecomove.dto.request.UserRequestDTO;
import com.example.ecomove.dto.response.AuthResponseDTO;
import com.example.ecomove.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // POST /api/v1/auth/register
    // Inscription d'un nouvel utilisateur
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO>
    inscrire(
            @Valid @RequestBody
            UserRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.inscrire(dto));
    }

    // POST /api/v1/auth/login
    // Connexion d'un utilisateur existant
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO>
    connecter(
            @Valid @RequestBody
            AuthRequestDTO dto) {

        return ResponseEntity
                .ok(authService.connecter(dto));
    }
}
