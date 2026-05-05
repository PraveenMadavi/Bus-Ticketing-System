package com.client.busticket.bus_service.repository;

import com.client.busticket.bus_service.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
//     Trip findByBusId(Long busId);

}
