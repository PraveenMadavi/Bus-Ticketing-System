package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Route;
import com.client.busticket.bus_service.records.RouteInfo;
import com.client.busticket.bus_service.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/route")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Route> addRoute(@RequestBody RouteInfo routeInfo) {
        Route route = routeService.saveRoute(routeInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }

}
