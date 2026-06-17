package com.client.busticket.bus_service.repository;

import com.client.busticket.bus_service.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findBySourceAndDestination(String source, String destination);
    List<Route> findBySource(String source);
    List<Route> findByDestination(String destination);
}
