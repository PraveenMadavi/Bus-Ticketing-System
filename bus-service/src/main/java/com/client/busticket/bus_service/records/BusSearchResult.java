package com.client.busticket.bus_service.records;

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

