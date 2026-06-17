package com.client.busticket.passenger_service.record;

public record BusInfo(
        Long id,
        String busNumber,
        String busType,
        int totalSeats) {
}
