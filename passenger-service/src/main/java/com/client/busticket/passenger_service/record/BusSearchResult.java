package com.client.busticket.passenger_service.record;

import java.time.LocalDateTime;

public record BusSearchResult(
        Long tripId,
        Long busId,
        String busNumber,
        String busType,
        int totalSeats,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        String source,
        String destination,
        String driverName,
        String conductorName,
        int availableSeats
) {
}

