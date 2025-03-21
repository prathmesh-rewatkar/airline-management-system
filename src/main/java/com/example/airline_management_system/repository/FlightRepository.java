package com.example.airline_management_system.repository;

import com.example.airline_management_system.dto.FlightDTO;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlightRepository {

    private final List<FlightDTO> flights = new ArrayList<>();

    @PostConstruct
    public void init() {

        flights.add(new FlightDTO("F100", "Air India", "Mumbai", "Delhi", LocalDate.of(2025, 4, 10), null, null, 5000, "Scheduled"));
        flights.add(new FlightDTO("F101", "Indigo", "Bangalore", "Kolkata", LocalDate.of(2025, 4, 11), null, null, 4500, "Scheduled"));
        flights.add(new FlightDTO("F102", "SpiceJet", "Chennai", "Hyderabad", LocalDate.of(2025, 4, 12), null, null, 4800, "Scheduled"));
        flights.add(new FlightDTO("F103", "Vistara", "Delhi", "Mumbai", LocalDate.of(2025, 4, 13), null, null, 5200, "Scheduled"));
        flights.add(new FlightDTO("F104", "GoAir", "Pune", "Bangalore", LocalDate.of(2025, 4, 14), null, null, 4300, "Scheduled"));
    }

    public List<FlightDTO> getAllFlights(String sort) {
        return sort.equalsIgnoreCase("asc") ? flights.stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .collect(Collectors.toList()) :
                flights.stream().sorted((a, b) -> b.getId().compareTo(a.getId())).collect(Collectors.toList());
    }

    public FlightDTO getFlightById(String id) {
        return flights.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

    public List<FlightDTO> getFlightsByDate(LocalDate date) {
        return flights.stream().filter(f -> f.getDate().equals(date)).collect(Collectors.toList());
    }

    public void addFlight(FlightDTO flight) {
        if (flights.stream().anyMatch(f -> f.getId().equals(flight.getId()))) {
            throw new RuntimeException("Flight with ID " + flight.getId() + " already exists.");
        }
        flights.add(flight);
    }

}
