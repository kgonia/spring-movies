package com.kss.springmovies.runners;

import com.kss.springmovies.dto.Ticket;
import com.kss.springmovies.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class AddTicketRunner implements CommandLineRunner {

    private final TicketService ticketService;

    @Autowired
    public AddTicketRunner(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public void run(String... args) throws Exception {

        Ticket ticket = new Ticket();
        ticket.setMinimalAge(10);
        ticket.setPrice(BigDecimal.valueOf(25));
        ticket.setStartDate(LocalDate.now());
        ticket.setEndDate(LocalDate.now().plusDays(90));

        Ticket ticket1 = new Ticket();
        ticket1.setMinimalAge(18);
        ticket1.setPrice(BigDecimal.valueOf(30));
        ticket1.setStartDate(LocalDate.now());
        ticket1.setEndDate(LocalDate.now().plusDays(90));

        this.ticketService.addTicket(ticket);
        this.ticketService.addTicket(ticket1);
    }
}
