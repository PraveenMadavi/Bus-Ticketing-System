package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.records.JourneyInfo;
import com.client.busticket.bus_service.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public")
public class PublicController {

    private final BusService busService;

//    @GetMapping("/api/v1/public/search-buses")
//    List<BusInfo> fetchBuses(JourneyInfo journeyInfo);
    @GetMapping("/search-buses")
    public ResponseEntity<?> searchBuses(@RequestBody JourneyInfo journeyInfo) {
        // Get bus list from bus service based on the journey info
        // This will involve calling the bus service's API to get the available buses for the given journey info

        return ResponseEntity.ok("List of available buses for the given journey info");
    }

//    @GetMapping("/api/v1/public/book-seats")
//    void bookSeats(Long busId, List<Integer> seatNumbers);
//
//    @GetMapping("/api/v1/public/cancel-seats")
//    void cancelSeats(Long busId, List<Integer> seatNumbers);
//
//    @GetMapping("/api/v1/public/check-bus-status")
//    String checkBusStatus(Long busId);
}
