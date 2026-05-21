package com.client.busticket.bus_service.repository;

import com.client.busticket.bus_service.entity.Trip;
import com.client.busticket.bus_service.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByRoute(Route route);

    @Query("SELECT t FROM Trip t WHERE t.route = :route AND CAST(t.departureTime AS date) = :date")
    List<Trip> findTripsByRouteAndDate(@Param("route") Route route, @Param("date") LocalDate date);
}
