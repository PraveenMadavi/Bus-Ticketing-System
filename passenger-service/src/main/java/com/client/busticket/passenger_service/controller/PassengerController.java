package com.client.busticket.passenger_service.controller;

import com.client.busticket.passenger_service.record.JourneyInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/book-ticket")
    public ResponseEntity<?> bookTicket(@RequestBody JourneyInfo journeyInfo){
        //save the passenger info and journey info in the database
        //call the booking service to book the ticket
        //return the booking confirmation to the client
        return ResponseEntity.ok("Booking confirmation");
    }

}
