package com.client.busticket.bus_service.entity;

import com.client.busticket.bus_service.enums.BusType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;

    @Enumerated(EnumType.STRING)
    private BusType busType;

    private int totalSeats;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Seat> seats;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Trip> trips;

    public void addSeat(Seat seat) {
        if (seats==null) {
            seats = new ArrayList<>();
        }
        seats.add(seat);
        seat.setBus(this);
    }
}