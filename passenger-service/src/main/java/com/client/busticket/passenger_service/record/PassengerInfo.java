package com.client.busticket.passenger_service.record;

import com.client.busticket.passenger_service.enums.Gender;

import java.util.List;

public record PassengerInfo(
        List<Integer> selectedSeatNumber,
        List<String> passengerNames,
        List<Integer> passengerAges,
        List<Gender> passengerGenders,
        String contactNumber,
        String email
) {
}
