package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Conductor;
import com.client.busticket.bus_service.records.ConductorInfo;
import com.client.busticket.bus_service.repository.ConductorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConductorService {
    private final ConductorRepository conductorRepository;

    public Conductor saveConductor(ConductorInfo conductorInfo){
        Conductor conductor = new Conductor();
        conductor.setName(conductorInfo.name());
        conductor.setContactNumber(conductorInfo.contactNumber());
        return conductorRepository.save(conductor);
    }
}
