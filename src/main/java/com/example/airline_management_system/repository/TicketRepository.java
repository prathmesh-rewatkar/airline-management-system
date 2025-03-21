package com.example.airline_management_system.repository;

import com.example.airline_management_system.dto.TicketDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {
    private final List<TicketDTO> tickets = new ArrayList<>();

    public List<TicketDTO> getAllTickets() {
        return new ArrayList<>(tickets);
    }
}
