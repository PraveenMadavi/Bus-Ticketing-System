package com.client.busticket.bus_service.repository;

import com.client.busticket.bus_service.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
     Seat findByBusIdAndSeatNumber(Long busId, String seatNumber);
}
