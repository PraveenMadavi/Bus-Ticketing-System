package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Seat;
import com.client.busticket.bus_service.records.SeatInfo;
import com.client.busticket.bus_service.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seats")
public class SeatController {

    private final SeatService seatService;

    @PostMapping("/seat")
    public ResponseEntity<Seat> createSeat(@RequestParam SeatInfo seatInfo) {
        Seat createdSeat = seatService.createSeat(seatInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSeat);
    }


}
