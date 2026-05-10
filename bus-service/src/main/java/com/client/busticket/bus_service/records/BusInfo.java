package com.client.busticket.bus_service.records;

public record BusInfo(
        String busNumber,
        String busType,
        int totalSeats) {
}
