package com.client.busticket.bus_service.records;

public record BusInfo(
        Long id,
        String busNumber,
        String busType,
        int totalSeats) {

    public BusInfo(String busNumber, String busType, int totalSeats) {
        this(null, busNumber, busType, totalSeats);
    }
}
