package com.client.busticket.passenger_service.configuration;

import com.client.busticket.passenger_service.record.BusInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "bus-service")
public interface BusFeignClients {

    @GetMapping("/api/v1/buses/all")
    List<BusInfo> getAllBuses();

    @GetMapping("/api/v1/buses/{busId}")
    BusInfo getBusById(Long busId);

//    BusInfo createBus(BusInfo busInfo);


}
