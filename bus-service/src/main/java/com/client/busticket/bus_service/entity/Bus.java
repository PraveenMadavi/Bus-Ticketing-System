package com.client.busticket.bus_service.entity;

import com.client.busticket.bus_service.enums.BusType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;

    //enum for bus type (e.g., AC, Non-AC, Sleeper)
    @Enumerated(EnumType.STRING)
    private BusType busType;

    private int totalSeats;

}
