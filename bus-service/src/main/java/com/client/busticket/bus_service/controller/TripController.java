package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Trip;
import com.client.busticket.bus_service.records.TripInfo;
import com.client.busticket.bus_service.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping("/trip")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Trip> createTrip(@RequestBody TripInfo tripInfo) {
        // Sets root and bus to the trip
        Trip trip = tripService.saveTrip(tripInfo);
        return ResponseEntity.ok(new Trip());
    }

    @GetMapping("/trip/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        Trip trip = tripService.getTripById(id);
        return ResponseEntity.ok(trip);
    }
}
