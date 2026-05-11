package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Ticket;
import com.client.busticket.bus_service.records.TicketInfo;
import com.client.busticket.bus_service.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public Ticket saveTicket(TicketInfo ticketInfo) {
        Ticket ticket = new Ticket();
        //fetch booking and seat number from booking service and seat service

//        ticket.setBooking();
//        ticket.setSeatNumber();
        return ticketRepository.save(ticket);
    }
}
