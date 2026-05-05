package com.client.busticket.bus_service.repository;

import com.client.busticket.bus_service.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
//        Booking findByBookingReference(String bookingReference);
//
//        Booking findByUserId(Long userId);


}
