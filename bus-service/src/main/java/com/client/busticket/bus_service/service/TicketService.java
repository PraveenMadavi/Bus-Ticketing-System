package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Ticket;
import com.client.busticket.bus_service.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
