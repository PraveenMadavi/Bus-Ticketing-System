package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Trip;
import com.client.busticket.bus_service.records.TripInfo;
import com.client.busticket.bus_service.service.TripService;
import jdk.jshell.Snippet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping("/trip")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Trip> createTrip(@RequestBody TripInfo tripInfo) {
        // Fetch entities and create trip with full validation and availability checks
        Trip trip = tripService.saveTrip(tripInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(trip);
    }

    @GetMapping("/trip/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        Trip trip = tripService.getTripById(id);
        return ResponseEntity.ok(trip);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return ResponseEntity.ok(trips);
    }

    @PutMapping("/trip/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTrip(@PathVariable Long id, @RequestBody TripInfo tripInfo) {
        Trip updatedTrip = tripService.updateTrip(id, tripInfo);
        return ResponseEntity.ok(updatedTrip);
    }
}
