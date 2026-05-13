package com.client.busticket.passenger_service.record;

import java.time.LocalDateTime;

public record JourneyInfo(
        String from,
        String to,
        LocalDateTime travelDate
) {
}
