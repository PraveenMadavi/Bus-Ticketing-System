package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Bus;
import com.client.busticket.bus_service.entity.Route;
import com.client.busticket.bus_service.entity.Trip;
import com.client.busticket.bus_service.records.TripInfo;
import com.client.busticket.bus_service.repository.BusRepository;
import com.client.busticket.bus_service.repository.RouteRepository;
import com.client.busticket.bus_service.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripService {

    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final TripRepository tripRepository;

    //Sets route and bus for a trip and saves it to the database
    public Trip saveTrip(TripInfo tripInfo) {
        // Logic to save trip information to the database
        Route route = routeRepository.findById(tripInfo.routeId()).orElseThrow();
        Bus bus = busRepository.findById(tripInfo.busId()).orElseThrow();
        Trip trip = new Trip();
        trip.setRoute(route);
        trip.setBus(bus);
        return tripRepository.save(trip);
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElseThrow();
    }
}
