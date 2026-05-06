package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Conductor;
import com.client.busticket.bus_service.records.ConductorInfo;
import com.client.busticket.bus_service.service.ConductorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
