package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Bus;
import com.client.busticket.bus_service.records.BusInfo;
import com.client.busticket.bus_service.records.BusSearchResult;
import com.client.busticket.bus_service.records.DropdownDto;
import com.client.busticket.bus_service.records.JourneyInfo;
import com.client.busticket.bus_service.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buses")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    @PostMapping("/bus")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Bus> addBus(@RequestBody BusInfo busInfo) {
        Bus bus = busService.createBus(busInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(bus);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/dropdown")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DropdownDto>> AllBuses() {
        List<Bus> buses = busService.getAllBuses();
        List<DropdownDto> dropdownDto = buses.stream()
                .map(bus -> new DropdownDto(bus.getId(), bus.getBusNumber()))
                .toList();
        return ResponseEntity.ok(dropdownDto);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public String test() {
        return "Bus Service is up and running! This is a test endpoint.";
    }

//    /**
//     * Search for buses on a specific route on a given date
//     * Public endpoint accessible to all users
//     * Returns list of available buses (basic info)
//     * @param journeyInfo Contains from (source), to (destination), and travelDate
//     * @return ResponseEntity containing list of buses or error message
//     */
//    @PostMapping("/public/search-buses")
//    public ResponseEntity<?> searchBuses(@RequestBody JourneyInfo journeyInfo) {
//        try {
//            List<Bus> buses = busService.searchBusesByJourney(journeyInfo);
//            List<BusInfo> busInfoList = buses.stream()
//                    .map(bus -> new BusInfo(bus.getId(), bus.getBusNumber(), bus.getBusType().toString(), bus.getTotalSeats()))
//                    .toList();
//            return ResponseEntity.ok(busInfoList);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error searching for buses: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Search for buses with detailed trip and availability information
//     * Public endpoint accessible to all users
//     * Returns comprehensive trip details including departure/arrival times, driver, conductor, and available seats
//     * @param journeyInfo Contains from (source), to (destination), and travelDate
//     * @return ResponseEntity containing list of detailed trip information or error message
//     */
//    @PostMapping("/public/search-buses-detailed")
//    public ResponseEntity<?> searchBusesDetailed(@RequestBody JourneyInfo journeyInfo) {
//        try {
//            List<BusSearchResult> searchResults = busService.searchBusesWithTripDetails(journeyInfo);
//            if (searchResults.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body("No buses found for route: " + journeyInfo.from() + " to " + journeyInfo.to() + " on " + journeyInfo.travelDate());
//            }
//            return ResponseEntity.ok(searchResults);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error searching for buses: " + e.getMessage());
//        }
//    }

}
