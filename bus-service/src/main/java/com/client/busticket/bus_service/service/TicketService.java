package com.client.busticket.bus_service.service;

import com.client.busticket.bus_service.entity.Ticket;
import com.client.busticket.bus_service.records.TicketInfo;
import com.client.busticket.bus_service.repository.BookingRepository;
import com.client.busticket.bus_service.repository.SeatRepository;
import com.client.busticket.bus_service.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;

    public Ticket saveTicket(TicketInfo ticketInfo) {
        Ticket ticket = new Ticket();
        //fetch booking and seat number from booking service and seat service
        seatRepository.findById(ticketInfo.seatId()).ifPresent(seat -> ticket.setSeatNumber(seat.getSeatNumber()));
        bookingRepository.findById(ticketInfo.bookingId()).ifPresent(ticket::setBooking);
        return ticketRepository.save(ticket);
    }
}
