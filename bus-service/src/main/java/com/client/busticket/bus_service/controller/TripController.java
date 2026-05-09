package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Trip;
import com.client.busticket.bus_service.records.TripInfo;
import com.client.busticket.bus_service.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    public ResponseEntity<Trip> createTrip(@RequestBody TripInfo tripInfo) {
        // Implementation for creating a trip

        Trip trip = tripService.saveTrip(tripInfo);

        return ResponseEntity.ok(new Trip());
    }
}
