package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Driver;
import com.client.busticket.bus_service.records.DriverInfo;
import com.client.busticket.bus_service.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Driver> getDriverById(Long id) {
        Driver driver = driverService.getDriverById(id);
        return ResponseEntity.ok(driver);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @PutMapping("/driver/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody DriverInfo driverInfo) {
        Driver updatedDriver = driverService.updateDriver(id, driverInfo);
        return ResponseEntity.ok(updatedDriver);
    }

}
