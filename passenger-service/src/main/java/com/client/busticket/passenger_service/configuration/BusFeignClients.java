package com.client.busticket.passenger_service.configuration;

import com.client.busticket.passenger_service.record.BusInfo;
import com.client.busticket.passenger_service.record.JourneyInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "bus-service")
public interface BusFeignClients {

    @GetMapping("/api/v1/public/search-buses")
    List<BusInfo> fetchBuses(JourneyInfo journeyInfo);

    @GetMapping("/api/v1/public/book-seats")
    void bookSeats(Long busId, List<Integer> seatNumbers);

    @GetMapping("/api/v1/public/cancel-seats")
    void cancelSeats(Long busId, List<Integer> seatNumbers);

    @GetMapping("/api/v1/public/check-bus-status")
    String checkBusStatus(Long busId);

    @GetMapping("/api/v1/buses/all")
    List<BusInfo> getAllBuses();

    @GetMapping("/api/v1/buses/{busId}")
    BusInfo getBusById(Long busId);

}
