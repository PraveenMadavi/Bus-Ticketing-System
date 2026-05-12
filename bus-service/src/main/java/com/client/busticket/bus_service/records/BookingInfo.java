package com.client.busticket.bus_service.records;

import com.client.busticket.bus_service.enums.BookingStatus;
import com.client.busticket.bus_service.enums.PaymentStatus;

import java.math.BigDecimal;

public record BookingInfo(
        Long userId,
        Long tripId,
        BigDecimal amount,
        BookingStatus bookingStatus,
        PaymentStatus paymentStatus
) {
}
