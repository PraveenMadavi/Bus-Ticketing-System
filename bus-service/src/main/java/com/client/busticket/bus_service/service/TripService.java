package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.*;
import com.client.busticket.bus_service.records.TripInfo;
import com.client.busticket.bus_service.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final TripRepository tripRepository;
    private final DriverRepository driverRepository;
    private final ConductorRepository conductorRepository;

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

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip updateTrip(Long id, TripInfo tripInfo) {
        Driver driver = driverRepository.findById(tripInfo.driverId()).orElseThrow();
        Conductor conductor = conductorRepository.findById(tripInfo.conductorId()).orElseThrow();
        Trip trip = tripRepository.findById(id).orElseThrow();
        //Setup cascade update for driver and conductor
        trip.setDriver(driver);
        trip.setConductor(conductor);
        trip.setDepartureTime(tripInfo.departureTime());
        trip.setArrivalTime(tripInfo.arrivalTime());
        return tripRepository.save(trip);
    }
}
