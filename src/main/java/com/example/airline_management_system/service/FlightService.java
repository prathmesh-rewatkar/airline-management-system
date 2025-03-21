package com.example.airline_management_system.service;

import com.example.airline_management_system.dto.FlightDTO;
import com.example.airline_management_system.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {

        this.flightRepository = flightRepository;
    }

    public List<FlightDTO> getAllFlights(String sort) {

        return flightRepository.getAllFlights(sort);
    }

    public FlightDTO getFlightById(String id) {
        FlightDTO flight = flightRepository.getFlightById(id);
        if (flight == null) {
            throw new RuntimeException("Flight with ID " + id + " not found.");
        }
        return flight;
    }

    public List<FlightDTO> getFlightsByDate(LocalDate date) {
        List<FlightDTO> flights = flightRepository.getFlightsByDate(date);
        if (flights.isEmpty()) {
            throw new RuntimeException("No flights found for date: " + date);
        }
        return flights;
    }
}
