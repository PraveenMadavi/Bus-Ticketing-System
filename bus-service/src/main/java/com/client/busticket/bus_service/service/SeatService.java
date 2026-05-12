package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Seat;
import com.client.busticket.bus_service.records.SeatInfo;
import com.client.busticket.bus_service.repository.BusRepository;
import com.client.busticket.bus_service.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final BusRepository busRepository;

    public Seat createSeat(SeatInfo seatInfo) {
        Seat seat = new Seat();
        busRepository.findById(seatInfo.busId()).ifPresent(seat::setBus);
        seat.setSeatNumber(seatInfo.seatNumber());
        return seatRepository.save(seat);
    }
}
