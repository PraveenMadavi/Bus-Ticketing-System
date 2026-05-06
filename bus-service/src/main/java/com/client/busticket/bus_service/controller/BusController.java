package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Bus;
import com.client.busticket.bus_service.records.BusInfo;
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
    @PreAuthorize("hasAuthority('')")
    public ResponseEntity<Bus> addBus(@RequestBody BusInfo busInfo) {
        Bus bus = busService.createBus(busInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(bus);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public String test() {
        return "Bus Service is up and running! This is a test endpoint.";
    }

}
