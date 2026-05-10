package com.client.busticket.bus_service.service;


import com.client.busticket.bus_service.entity.Driver;
import com.client.busticket.bus_service.records.DriverInfo;
import com.client.busticket.bus_service.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    public Driver saveDriver(DriverInfo driverInfo) {
        Driver driver = new Driver();
        driver.setName(driverInfo.name());
        driver.setLicenseNumber(driverInfo.licenseNumber());
        driver.setContactNumber(driverInfo.contactNumber());
        return driverRepository.save(driver);
    }
}
