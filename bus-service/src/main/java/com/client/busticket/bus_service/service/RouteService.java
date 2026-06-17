package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Route;
import com.client.busticket.bus_service.records.RouteInfo;
import com.client.busticket.bus_service.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    public Route saveRoute(RouteInfo routeInfo){
        Route route = new Route();
        route.setSource(routeInfo.source());
        route.setDestination(routeInfo.destination());
        route.setDistance(routeInfo.distance());
        return routeRepository.save(route);
    }


    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElseThrow();
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
}
