package com.client.busticket.passenger_service.configuration;

import com.client.busticket.passenger_service.record.BusInfo;
import com.client.busticket.passenger_service.record.BusSearchResult;
import com.client.busticket.passenger_service.record.JourneyInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "bus-service")
public interface BusFeignClients {

    @PostMapping("/api/v1/public/search-buses")
    List<BusSearchResult> fetchBuses(@RequestBody JourneyInfo journeyInfo);

    @GetMapping("/api/v1/buses/all")
    List<BusInfo> getAllBuses();

    @GetMapping("/api/v1/buses/{busId}")
    BusInfo getBusById(@PathVariable("busId") Long busId);

}
