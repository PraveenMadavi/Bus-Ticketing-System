package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.*;
import com.client.busticket.bus_service.enums.BookingStatus;
import com.client.busticket.bus_service.enums.BusType;
import com.client.busticket.bus_service.records.BusInfo;
import com.client.busticket.bus_service.records.BusSearchResult;
import com.client.busticket.bus_service.records.JourneyInfo;
import com.client.busticket.bus_service.repository.*;
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
    private final BookingRepository bookingRepository;

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

//    /**
//     * Search for buses on a specific route on a given date
//     * @param journeyInfo Contains from (source), to (destination), and travelDate
//     * @return List of buses running on the specified route on the given date
//     */
//    public List<Bus> searchBusesByJourney(JourneyInfo journeyInfo) {
//        // Find the route matching the source and destination
//        Route route = routeRepository.findBySourceAndDestination(journeyInfo.from(), journeyInfo.to())
//                .orElseThrow(() -> new RuntimeException("Route not found for source: " + journeyInfo.from() + " and destination: " + journeyInfo.to()));
//
//        // Extract the date from LocalDateTime
//        LocalDate travelDate = journeyInfo.travelDate().toLocalDate();
//
//        // Find all trips for this route on the specified date
//        List<Trip> trips = tripRepository.findTripsByRouteAndDate(route, travelDate);
//
//        // Extract unique buses from the trips and return
//        return trips.stream()
//                .map(Trip::getBus)
//                .distinct()
//                .collect(Collectors.toList());
//    }

    /**
     * Search for buses with detailed trip information
     * @param journeyInfo Contains from (source), to (destination), and travelDate
     * @return List of detailed bus search results with trip information
     */
    public List<BusSearchResult> searchBusesWithTripDetails(JourneyInfo journeyInfo) {
        // Find the route matching the source and destination
        Route route = routeRepository.findBySourceAndDestination(journeyInfo.from(), journeyInfo.to())
                .orElseThrow(() -> new RuntimeException("Route not found for source: " + journeyInfo.from() + " and destination: " + journeyInfo.to()));

        // Extract the date from LocalDateTime
        LocalDate travelDate = journeyInfo.travelDate().toLocalDate();

        // Find all trips for this route on the specified date
        List<Trip> trips = tripRepository.findTripsByRouteAndDate(route, travelDate);

        // Convert trips to detailed search results
        return trips.stream()
                .map(this::tripToBusSearchResult)
                .collect(Collectors.toList());
    }

    /**
     * Calculate available seats for a specific trip
     * @param trip The trip to check
     * @return Number of available seats
     */
    private int calculateAvailableSeats(Trip trip) {
        if (trip == null || trip.getBus() == null) {
            return 0;
        }

        int totalSeats = trip.getBus().getTotalSeats();

        // Get all bookings for this trip that are confirmed
        List<Booking> bookings = bookingRepository.findAll().stream()
                .filter(booking -> booking.getTrip() != null &&
                        booking.getTrip().getId().equals(trip.getId()) &&
                        booking.getStatus() == BookingStatus.CONFIRMED)
                .collect(Collectors.toList());

        // Count all booked seats
        int bookedSeats = 0;
        for (Booking booking : bookings) {
            if (booking.getTickets() != null) {
                bookedSeats += booking.getTickets().size();
            }
        }

        return totalSeats - bookedSeats;
    }

    /**
     * Convert a Trip entity to a BusSearchResult record
     * @param trip The trip to convert
     * @return BusSearchResult with detailed information
     */
    private BusSearchResult tripToBusSearchResult(Trip trip) {
        Bus bus = trip.getBus();
        Route route = trip.getRoute();
        Driver driver = trip.getDriver();
        Conductor conductor = trip.getConductor();

        return new BusSearchResult(
                trip.getId(),
                bus.getId(),
                bus.getBusNumber(),
                bus.getBusType().toString(),
                bus.getTotalSeats(),
                trip.getDepartureTime(),
                trip.getArrivalTime(),
                route != null ? route.getSource() : "N/A",
                route != null ? route.getDestination() : "N/A",
                driver != null ? driver.getName() : "Not Assigned",
                conductor != null ? conductor.getName() : "Not Assigned",
                calculateAvailableSeats(trip)
        );
    }
}
