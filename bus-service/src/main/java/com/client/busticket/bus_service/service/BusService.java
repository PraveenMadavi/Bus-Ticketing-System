package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Bus;
import com.client.busticket.bus_service.entity.Seat;
import com.client.busticket.bus_service.enums.BusType;
import com.client.busticket.bus_service.records.BusInfo;
import com.client.busticket.bus_service.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus createBus(BusInfo busInfo) {
        Bus bus = new Bus();
        bus.setBusNumber(busInfo.busNumber());
        bus.setBusType(BusType.valueOf(busInfo.busType()));
        bus.setTotalSeats(busInfo.totalSeats());

        for (int i = 1; i <= bus.getTotalSeats(); i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            bus.addSeat(seat);
        }

        return busRepository.save(bus);
    }
}
