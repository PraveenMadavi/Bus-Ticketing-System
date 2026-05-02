package com.client.busticket.bus_service.repository;

import com.client.busticket.bus_service.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
     Driver findByLicenseNumber(String licenseNumber);
}
