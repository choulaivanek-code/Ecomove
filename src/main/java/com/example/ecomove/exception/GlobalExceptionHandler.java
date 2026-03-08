package com.example.ecomove.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // Gère les ressources introuvables → 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>>
    handleResourceNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "statut", 404,
                        "erreur", "Not Found",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    // Gère les violations de règles métier
    // de réservation → 409
    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<Map<String, Object>>
    handleReservation(
            ReservationException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "statut", 409,
                        "erreur", "Conflict",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    // Gère les erreurs d'authentification → 401
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Map<String, Object>>
    handleAuth(AuthException ex) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "statut", 401,
                        "erreur", "Unauthorized",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    // Gère les erreurs de véhicule → 409
    @ExceptionHandler(VehiculeException.class)
    public ResponseEntity<Map<String, Object>>
    handleVehicule(VehiculeException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "statut", 409,
                        "erreur", "Conflict",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    // Gère toutes les autres erreurs → 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>
    handleGeneral(Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "statut", 500,
                        "erreur", "Internal Server Error",
                        "message", "Une erreur inattendue "
                                + "s'est produite",
                        "timestamp", LocalDateTime.now()
                ));
    }
}
