package com.client.busticket.bus_service.repository;

import com.client.busticket.bus_service.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {
     Conductor findByName(String name);
}
