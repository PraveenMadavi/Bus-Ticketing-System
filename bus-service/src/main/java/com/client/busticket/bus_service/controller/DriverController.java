package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Driver;
import com.client.busticket.bus_service.records.DriverInfo;
import com.client.busticket.bus_service.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/driver")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Driver> addDriver(@RequestBody DriverInfo driverInfo) {
        // Implementation for adding a driver
        Driver driver = driverService.saveDriver(driverInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(driver);
    }
}
