package com.client.busticket.bus_service.service;


import com.client.busticket.bus_service.entity.Driver;
import com.client.busticket.bus_service.entity.Trip;
import com.client.busticket.bus_service.records.DriverInfo;
import com.client.busticket.bus_service.repository.DriverRepository;
import com.client.busticket.bus_service.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final TripRepository tripRepository;

    public Driver saveDriver(DriverInfo driverInfo) {
        Driver driver = new Driver();
        driver.setName(driverInfo.name());
        driver.setLicenseNumber(driverInfo.licenseNumber());
        driver.setContactNumber(driverInfo.contactNumber());
        return driverRepository.save(driver);
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElseThrow();
    }

    public Driver updateDriver(Long id, DriverInfo driverInfo) {
        Driver driver = driverRepository.findById(id).orElseThrow();
        driver.setName(driverInfo.name());
        driver.setLicenseNumber(driverInfo.licenseNumber());
        driver.setContactNumber(driverInfo.contactNumber());
        return driverRepository.save(driver);
    }

    public void assignTrip(Long driverId, Long tripId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        Trip trip = tripRepository.findById(tripId).orElseThrow();
        driver.getTrips().add(trip);
        driverRepository.save(driver);
    }
}
