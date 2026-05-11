package com.client.busticket.bus_service.controller;

import com.client.busticket.bus_service.entity.Ticket;
import com.client.busticket.bus_service.records.TicketInfo;
import com.client.busticket.bus_service.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    //exposed endpoint for booking a ticket, it will be called by the booking service after the payment is successful
    @PostMapping("/ticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketInfo ticketInfo) {
        Ticket ticket = ticketService.saveTicket(ticketInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

}
