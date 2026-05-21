package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Bus;
import com.client.busticket.bus_service.entity.Route;
import com.client.busticket.bus_service.entity.Seat;
import com.client.busticket.bus_service.entity.Trip;
import com.client.busticket.bus_service.enums.BusType;
import com.client.busticket.bus_service.records.BusInfo;
import com.client.busticket.bus_service.records.JourneyInfo;
import com.client.busticket.bus_service.repository.BusRepository;
import com.client.busticket.bus_service.repository.RouteRepository;
import com.client.busticket.bus_service.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final TripRepository tripRepository;

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus createBus(BusInfo busInfo) {
        Bus bus = new Bus();
        bus.setBusNumber(busInfo.busNumber());
        bus.setBusType(BusType.valueOf(busInfo.busType()));
        bus.setTotalSeats(busInfo.totalSeats());

        for (int i = 1; i <= bus.getTotalSeats(); i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            bus.addSeat(seat);
        }

        return busRepository.save(bus);
    }

    /**
     * Search for buses on a specific route on a given date
     * @param journeyInfo Contains from (source), to (destination), and travelDate
     * @return List of buses running on the specified route on the given date
     */
    public List<Bus> searchBusesByJourney(JourneyInfo journeyInfo) {
        // Find the route matching the source and destination
        Route route = routeRepository.findBySourceAndDestination(journeyInfo.from(), journeyInfo.to())
                .orElseThrow(() -> new RuntimeException("Route not found for source: " + journeyInfo.from() + " and destination: " + journeyInfo.to()));

        // Extract the date from LocalDateTime
        LocalDate travelDate = journeyInfo.travelDate().toLocalDate();

        // Find all trips for this route on the specified date
        List<Trip> trips = tripRepository.findTripsByRouteAndDate(route, travelDate);

        // Extract unique buses from the trips and return
        return trips.stream()
                .map(Trip::getBus)
                .distinct()
                .collect(Collectors.toList());
    }
}
