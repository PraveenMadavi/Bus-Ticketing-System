package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Conductor;
import com.client.busticket.bus_service.records.ConductorInfo;
import com.client.busticket.bus_service.service.ConductorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conductors")
@RequiredArgsConstructor
public class ConductorController {
    private final ConductorService conductorService;

    @PostMapping("/conductor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Conductor> addConductor(@RequestBody ConductorInfo conductorInfo) {
        Conductor conductor = conductorService.saveConductor(conductorInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(conductor);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getConductorById(Long id) {
        Conductor conductor = conductorService.getConductorById(id);
        return ResponseEntity.ok(conductor);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Conductor>> getAllConductors() {
        return conductorService.getAllConductors();
    }

}
