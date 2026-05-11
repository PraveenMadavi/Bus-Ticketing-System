package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Conductor;
import com.client.busticket.bus_service.entity.Trip;
import com.client.busticket.bus_service.records.ConductorInfo;
import com.client.busticket.bus_service.repository.ConductorRepository;
import com.client.busticket.bus_service.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConductorService {
    private final ConductorRepository conductorRepository;
    private final TripRepository tripRepository;

    public Conductor saveConductor(ConductorInfo conductorInfo){
        Conductor conductor = new Conductor();
        conductor.setName(conductorInfo.name());
        conductor.setContactNumber(conductorInfo.contactNumber());
        return conductorRepository.save(conductor);
    }

    public Conductor getConductorById(Long id){
        return conductorRepository.findById(id).orElseThrow();
    }

    public ResponseEntity<List<Conductor>> getAllConductors() {
        List<Conductor> conductors = conductorRepository.findAll();
        return ResponseEntity.ok(conductors);
    }

    public void assignTrip(Long conductorId, Long tripId) {
        Conductor conductor = conductorRepository.findById(conductorId).orElseThrow();
        Trip trip = tripRepository.findById(tripId).orElseThrow();
        conductor.getTrips().add(trip);
        conductorRepository.save(conductor);
    }
}
