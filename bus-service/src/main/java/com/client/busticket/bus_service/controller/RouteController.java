package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Route;
import com.client.busticket.bus_service.records.RouteInfo;
import com.client.busticket.bus_service.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/route")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Route> addRoute(@RequestBody RouteInfo routeInfo) {
        Route route = routeService.saveRoute(routeInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        Route route = routeService.getRouteById(id);
        return ResponseEntity.ok(route);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

}
