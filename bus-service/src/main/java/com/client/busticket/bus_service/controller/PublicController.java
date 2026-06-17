package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.records.BusSearchResult;
import com.client.busticket.bus_service.records.JourneyInfo;
import com.client.busticket.bus_service.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public")
public class PublicController {

    private final BusService busService;

    /**
     * Search for buses with detailed trip information
     * Public endpoint - accessible to all users without authentication
     * Returns comprehensive trip details including departure/arrival times, driver, conductor, and available seats
     * @param journeyInfo Contains from (source), to (destination), and travelDate
     * @return ResponseEntity containing list of available buses with detailed trip information
     */
    @PostMapping("/search-buses")
    public ResponseEntity<?> searchBuses(@RequestBody JourneyInfo journeyInfo) {
        try {
            // Get bus list from bus service based on the journey info
            List<BusSearchResult> searchResults = busService.searchBusesWithTripDetails(journeyInfo);

            if (searchResults.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No buses found for route: " + journeyInfo.from() + " to " + journeyInfo.to() + " on " + journeyInfo.travelDate());
            }
            return ResponseEntity.ok(searchResults);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error searching for buses: " + e.getMessage());
        }
    }

}
