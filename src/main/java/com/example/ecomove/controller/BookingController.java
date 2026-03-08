package com.example.ecomove.controller;

import com.example.ecomove.dto.request.BookingRequestDTO;
import com.example.ecomove.dto.response.BookingResponseDTO;
import com.example.ecomove.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // POST /api/v1/bookings
    // Créer une réservation
    // C'est l'endpoint principal de l'épreuve
    // Body : {"userId": 123, "tripId": 456}
    // Codes : 201, 400, 401, 404, 409, 500
    @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDTO>
    creerReservation(
            @Valid @RequestBody
            BookingRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookingService
                        .creerReservation(
                                dto.getUserId(),
                                dto.getTripId()));
    }

    // GET /api/v1/bookings/user/{userId}
    // Récupérer les réservations
    // d'un passager
    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>>
    recupererParPassager(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                bookingService
                        .recupererParPassager(userId));
    }

    // PATCH /api/v1/bookings/{id}/annuler
    // Annuler une réservation
    @PatchMapping("/bookings/{id}/annuler")
    public ResponseEntity<BookingResponseDTO>
    annuler(@PathVariable Long id) {

        return ResponseEntity
                .ok(bookingService.annuler(id));
    }
}
