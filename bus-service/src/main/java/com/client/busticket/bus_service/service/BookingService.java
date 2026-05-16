package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Booking;
import com.client.busticket.bus_service.records.BookingInfo;
import com.client.busticket.bus_service.repository.BookingRepository;
import com.client.busticket.bus_service.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TripRepository tripRepository;

    public Booking saveBooking(BookingInfo bookingInfo) {
        Booking booking = new Booking();
        tripRepository.findById(bookingInfo.tripId()).ifPresent(booking::setTrip);
        booking.setUserId(bookingInfo.userId());
        booking.setTotalAmount(bookingInfo.amount());
        booking.setPaymentStatus(bookingInfo.paymentStatus());
        booking.setStatus(bookingInfo.bookingStatus());
        return bookingRepository.save(booking);
    }
}
