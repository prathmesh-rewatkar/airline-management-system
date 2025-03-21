package com.example.airline_management_system.service;

import com.example.airline_management_system.dto.TicketDTO;
import com.example.airline_management_system.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketDTO> getAllTickets() {
        return ticketRepository.getAllTickets();
    }
}
