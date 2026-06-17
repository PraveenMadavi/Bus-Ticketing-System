package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.*;
import com.client.busticket.bus_service.records.TripInfo;
import com.client.busticket.bus_service.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private static final Logger log = LoggerFactory.getLogger(TripService.class);

    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final TripRepository tripRepository;
    private final DriverRepository driverRepository;
    private final DriverService driverService;
    private final ConductorRepository conductorRepository;
    private final ConductorService conductorService;

    /**
     * Creates and saves a new Trip with all required entities and times.
     * Validates that departure < arrival, checks resource availability, and ensures driver/conductor are distinct.
     *
     * @param tripInfo containing routeId, busId, departureTime, arrivalTime, driverId, conductorId
     * @return saved Trip entity with all relationships populated
     * @throws IllegalArgumentException if any validation fails
     * @throws java.util.NoSuchElementException if any referenced entity is not found
     */
    public Trip saveTrip(TripInfo tripInfo) {
        log.info("Creating trip: route={}, bus={}, departure={}, arrival={}, driver={}, conductor={}",
                tripInfo.routeId(), tripInfo.busId(), tripInfo.departureTime(), tripInfo.arrivalTime(),
                tripInfo.driverId(), tripInfo.conductorId());

        // Validate time constraints
        if (tripInfo.departureTime().isAfter(tripInfo.arrivalTime()) || tripInfo.departureTime().isEqual(tripInfo.arrivalTime())) {
            throw new IllegalArgumentException("Departure time must be before arrival time");
        }

//        // Validate driver and conductor are different  //    NOT REQUIRED  CAUSE BOTH ARE DIFFERENT TABLES
//        if (tripInfo.driverId().equals(tripInfo.conductorId())) {
//            throw new IllegalArgumentException("Driver and conductor must be different persons");
//        }

        // Fetch all entities from database
        Route route = routeRepository.findById(tripInfo.routeId())
                .orElseThrow(() -> new IllegalArgumentException("Route not found with id: " + tripInfo.routeId()));

        Bus bus = busRepository.findById(tripInfo.busId())
                .orElseThrow(() -> new IllegalArgumentException("Bus not found with id: " + tripInfo.busId()));

        Driver driver = driverRepository.findById(tripInfo.driverId())
                .orElseThrow(() -> new IllegalArgumentException("Driver not found with id: " + tripInfo.driverId()));

        Conductor conductor = conductorRepository.findById(tripInfo.conductorId())
                .orElseThrow(() -> new IllegalArgumentException("Conductor not found with id: " + tripInfo.conductorId()));

        // Check bus availability: no overlapping trips for the same bus
        List<Trip> busTrips = tripRepository.findAll().stream()
                .filter(t -> t.getBus().getId().equals(bus.getId()) &&
                        t.getDepartureTime().isBefore(tripInfo.arrivalTime()) &&
                        t.getArrivalTime().isAfter(tripInfo.departureTime()))
                .toList();

        if (!busTrips.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Bus %d is already assigned between %s and %s",
                            bus.getId(), busTrips.get(0).getDepartureTime(), busTrips.get(0).getArrivalTime()));
        }

        // Check driver availability: no overlapping trips for the same driver
        List<Trip> driverTrips = tripRepository.findAll().stream()
                .filter(t -> t.getDriver() != null &&
                        t.getDriver().getId().equals(driver.getId()) &&
                        t.getDepartureTime().isBefore(tripInfo.arrivalTime()) &&
                        t.getArrivalTime().isAfter(tripInfo.departureTime()))
                .toList();

        if (!driverTrips.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Driver %d is already assigned between %s and %s",
                            driver.getId(), driverTrips.get(0).getDepartureTime(), driverTrips.get(0).getArrivalTime()));
        }

        // Create and populate Trip entity
        Trip trip = new Trip();
        trip.setRoute(route);
        trip.setBus(bus);
        trip.setDriver(driver);
        trip.setConductor(conductor);
        trip.setDepartureTime(tripInfo.departureTime());
        trip.setArrivalTime(tripInfo.arrivalTime());

        // Save trip
        Trip savedTrip = tripRepository.save(trip);
        log.info("Trip created successfully with id: {}", savedTrip.getId());

        // Assign trip to driver and conductor
        driverService.assignTrip(driver.getId(), savedTrip.getId());
        conductorService.assignTrip(conductor.getId(), savedTrip.getId());

        return savedTrip;
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found with id: " + id));
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    /**
     * Updates an existing trip's driver, conductor, and times.
     * Re-validates availability and constraints.
     *
     * @param id trip ID
     * @param tripInfo updated trip information
     * @return updated Trip entity
     */
    public Trip updateTrip(Long id, TripInfo tripInfo) {
        log.info("Updating trip {} with new driver={}, conductor={}, departure={}, arrival={}",
                id, tripInfo.driverId(), tripInfo.conductorId(), tripInfo.departureTime(), tripInfo.arrivalTime());

        // Validate time constraints
        if (tripInfo.departureTime().isAfter(tripInfo.arrivalTime()) || tripInfo.departureTime().isEqual(tripInfo.arrivalTime())) {
            throw new IllegalArgumentException("Departure time must be before arrival time");
        }

        // Validate driver and conductor are different
        if (tripInfo.driverId().equals(tripInfo.conductorId())) {
            throw new IllegalArgumentException("Driver and conductor must be different persons");
        }

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found with id: " + id));

        Driver driver = driverRepository.findById(tripInfo.driverId())
                .orElseThrow(() -> new IllegalArgumentException("Driver not found with id: " + tripInfo.driverId()));

        Conductor conductor = conductorRepository.findById(tripInfo.conductorId())
                .orElseThrow(() -> new IllegalArgumentException("Conductor not found with id: " + tripInfo.conductorId()));

        // Check bus availability (excluding current trip)
        List<Trip> busConflicts = tripRepository.findAll().stream()
                .filter(t -> !t.getId().equals(id) &&
                        t.getBus().getId().equals(trip.getBus().getId()) &&
                        t.getDepartureTime().isBefore(tripInfo.arrivalTime()) &&
                        t.getArrivalTime().isAfter(tripInfo.departureTime()))
                .toList();

        if (!busConflicts.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Bus %d is already assigned between %s and %s",
                            trip.getBus().getId(), busConflicts.get(0).getDepartureTime(), busConflicts.get(0).getArrivalTime()));
        }

        // Check driver availability (excluding current trip)
        List<Trip> driverConflicts = tripRepository.findAll().stream()
                .filter(t -> !t.getId().equals(id) &&
                        t.getDriver() != null &&
                        t.getDriver().getId().equals(driver.getId()) &&
                        t.getDepartureTime().isBefore(tripInfo.arrivalTime()) &&
                        t.getArrivalTime().isAfter(tripInfo.departureTime()))
                .toList();

        if (!driverConflicts.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Driver %d is already assigned between %s and %s",
                            driver.getId(), driverConflicts.get(0).getDepartureTime(), driverConflicts.get(0).getArrivalTime()));
        }

        // Update trip
        trip.setDriver(driver);
        trip.setConductor(conductor);
        trip.setDepartureTime(tripInfo.departureTime());
        trip.setArrivalTime(tripInfo.arrivalTime());

        Trip updatedTrip = tripRepository.save(trip);
        log.info("Trip {} updated successfully", id);

        // Assign trip to driver and conductor
        driverService.assignTrip(driver.getId(), trip.getId());
        conductorService.assignTrip(conductor.getId(), trip.getId());

        return updatedTrip;
    }
}
