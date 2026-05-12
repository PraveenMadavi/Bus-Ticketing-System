package com.client.busticket.bus_service.controller;


import com.client.busticket.bus_service.entity.Booking;
import com.client.busticket.bus_service.records.BookingInfo;
import com.client.busticket.bus_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingInfo bookingInfo) {
        Booking booking = bookingService.saveBooking(bookingInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }


}
