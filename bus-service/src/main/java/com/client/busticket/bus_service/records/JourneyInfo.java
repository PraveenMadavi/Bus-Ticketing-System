package com.client.busticket.bus_service.records;

import java.time.LocalDateTime;

public record JourneyInfo(
        String from,
        String to,
        LocalDateTime travelDate
) {
}
