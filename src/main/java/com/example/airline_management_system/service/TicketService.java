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

    public void createTicket(TicketDTO ticket) {
        validateTicket(ticket);

        if (ticketRepository.ticketExists(ticket.getId())) {
            throw new RuntimeException("Ticket with ID " + ticket.getId() + " already exists.");
        }

        ticketRepository.addTicket(ticket);
    }

    private void validateTicket(TicketDTO ticket) {

        if (ticket.getId() == null || ticket.getId().trim().isEmpty()) {
            throw new RuntimeException("Ticket ID cannot be null or empty.");
        }
        if (ticket.getPassengerName() == null || !ticket.getPassengerName().matches("^[A-Za-z ]+$")) {
            throw new RuntimeException("Passenger name must contain only letters and spaces.");
        }
        if (ticket.getEmail() == null || !ticket.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new RuntimeException("Invalid email format: " + ticket.getEmail());
        }
        if (ticket.getPhoneNumber() == null || !ticket.getPhoneNumber().matches("^\\d{10}$")) {
            throw new RuntimeException("Phone number must be exactly 10 digits.");
        }
        if (ticket.getFlightNumber() == null || ticket.getFlightNumber().trim().isEmpty()) {
            throw new RuntimeException("Flight number cannot be empty.");
        }
        if (ticket.getDepartureDate() == null || ticket.getDepartureDate().trim().isEmpty()) {
            throw new RuntimeException("Departure date cannot be empty.");
        }
        if (ticket.getSeatNumber() == null || ticket.getSeatNumber().trim().isEmpty()) {
            throw new RuntimeException("Seat number cannot be empty.");
        }
    }

    public TicketDTO getTicketById(String id) {
        TicketDTO ticket = ticketRepository.getTicketById(id);
        if (ticket == null) {
            throw new RuntimeException("Ticket with ID " + id + " not found.");
        }
        return ticket;
    }

    public void deleteTicket(String id) {
        if (!ticketRepository.ticketExists(id)) {
            throw new RuntimeException("Cannot delete. Ticket with ID " + id + " does not exist.");
        }
        ticketRepository.deleteTicket(id);
    }
}
