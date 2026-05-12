package com.client.busticket.bus_service.records;

import java.math.BigDecimal;

public record BookingInfo(
        Long userId,
        Long tripId,
        BigDecimal amount,
        String bookingStatus,
        String paymentStatus
) {
}
