package com.client.busticket.bus_service.repository;

import com.client.busticket.bus_service.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
     Bus findByBusNumber(String busNumber);

}
