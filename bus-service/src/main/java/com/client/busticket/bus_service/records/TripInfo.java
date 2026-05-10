package com.client.busticket.bus_service.records;

import java.time.LocalDateTime;

public record TripInfo(
        Long routeId,
        Long busId,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        Long driverId,
        Long conductorId
) {
}
