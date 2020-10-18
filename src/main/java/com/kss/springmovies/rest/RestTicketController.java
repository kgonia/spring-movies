package com.kss.springmovies.rest;

import com.kss.springmovies.dto.Ticket;
import com.kss.springmovies.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
public class RestTicketController {

    private final TicketService ticketService;

    @Autowired
    public RestTicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public Page<Ticket> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                @RequestParam(required = false, defaultValue = "10") Integer limit,
                                @RequestParam(required = false) Integer age,
                                @RequestParam(required = false) Integer price) {
        return this.ticketService.findAllBy(page, limit, age, price);
    }
}
