package com.example.ecomove.dto.request;

import jakarta.validation.constraints.*;

public class BookingRequestDTO {

    @NotNull(message = "L'id utilisateur est obligatoire")
    @Positive(message = "L'id doit être positif")
    private Long userId;

    @NotNull(message = "L'id du trajet est obligatoire")
    @Positive(message = "L'id doit être positif")
    private Long tripId;

    // GETTERS
    public Long getUserId() { return userId; }
    public Long getTripId() { return tripId; }

    // SETTERS
    public void setUserId(Long userId) { this.userId = userId; }
    public void setTripId(Long tripId) { this.tripId = tripId; }
}