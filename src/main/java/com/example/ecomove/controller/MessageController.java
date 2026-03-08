package com.example.ecomove.controller;

import com.example.ecomove.dto.request.MessageRequestDTO;
import com.example.ecomove.dto.response.MessageResponseDTO;
import com.example.ecomove.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // POST /api/v1/messages?expediteurId={id}
    // Envoyer un message
    @PostMapping
    public ResponseEntity<MessageResponseDTO>
    envoyer(
            @RequestParam Long expediteurId,
            @Valid @RequestBody
            MessageRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(messageService.envoyer(
                        expediteurId, dto));
    }

    // GET /api/v1/messages/recus/{userId}
    // Récupérer les messages reçus
    @GetMapping("/recus/{userId}")
    public ResponseEntity<List<MessageResponseDTO>>
    recupererMessagesRecus(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                messageService
                        .recupererMessagesRecus(userId));
    }

    // GET /api/v1/messages/non-lus/{userId}
    // Récupérer les messages non lus
    @GetMapping("/non-lus/{userId}")
    public ResponseEntity<List<MessageResponseDTO>>
    recupererNonLus(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                messageService
                        .recupererNonLus(userId));
    }

    // PATCH /api/v1/messages/{id}/lire
    // Marquer un message comme lu
    @PatchMapping("/{id}/lire")
    public ResponseEntity<MessageResponseDTO>
    marquerCommeLu(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                messageService.marquerCommeLu(id));
    }

    // GET /api/v1/messages/non-lus/count/{userId}
    // Compter les messages non lus
    // Utilisé pour le badge notifications
    @GetMapping("/non-lus/count/{userId}")
    public ResponseEntity<Map<String, Long>>
    compterNonLus(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                Map.of("nonLus",
                        messageService
                                .compterNonLus(userId)));
    }
}
