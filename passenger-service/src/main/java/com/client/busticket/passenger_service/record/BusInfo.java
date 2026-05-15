package com.client.busticket.passenger_service.record;

public record BusInfo(
        String busNumber,
        String busType,
        int totalSeats) {
}
