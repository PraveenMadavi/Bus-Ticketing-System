package com.client.busticket.passenger_service.controller;

import com.client.busticket.passenger_service.record.JourneyInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {

    @GetMapping("/search-buses")
    public ResponseEntity<?> searchBuses(@RequestBody JourneyInfo journeyInfo) {
        // Get bus list from bus service based on the journey info
        // This will involve calling the bus service's API to get the available buses for the given journey info
        // For now, we will return a placeholder response
        return ResponseEntity.ok("List of available buses for the given journey info");
    }

}
