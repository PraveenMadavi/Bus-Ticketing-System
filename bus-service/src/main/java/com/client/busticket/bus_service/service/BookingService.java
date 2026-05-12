package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Booking;
import com.client.busticket.bus_service.records.BookingInfo;
import com.client.busticket.bus_service.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking saveBooking(BookingInfo bookingInfo) {
        Booking booking = new Booking();

        return bookingRepository.save(booking);
    }
}
