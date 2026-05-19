package com.client.busticket.passenger_service.controller;

import com.client.busticket.passenger_service.configuration.BusFeignClients;
import com.client.busticket.passenger_service.record.BusInfo;
import com.client.busticket.passenger_service.record.JourneyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final BusFeignClients busFeignClients;

    @GetMapping("/search-buses")
    public ResponseEntity<?> searchBuses(@RequestBody JourneyInfo journeyInfo) {
        // Get bus list from bus service based on the journey info
        // This will involve calling the bus service's API to get the available buses for the given journey info

        return ResponseEntity.ok("List of available buses for the given journey info");
    }

    @PostMapping("/book-ticket")
    public ResponseEntity<?> bookTicket(@RequestBody JourneyInfo journeyInfo) {
        //save the passenger info and journey info in the database
        //call the booking service to book the ticket
        //return the booking confirmation to the client
        return ResponseEntity.ok("Booking confirmation");
    }

    @GetMapping("/get-all-buses")
    public ResponseEntity<?> getALlBuses() {
        List<BusInfo> allBuses = busFeignClients.getAllBuses();
        System.out.println("allBuses = " + allBuses);
        for (BusInfo bus : allBuses) {
            System.out.println("bus = " + bus);
        }
        return ResponseEntity.ok(allBuses);
    }

}
