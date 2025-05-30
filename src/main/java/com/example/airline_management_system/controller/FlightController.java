package com.example.airline_management_system.controller;

import com.example.airline_management_system.dto.FlightDTO;
import com.example.airline_management_system.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDTO> getAllFlights(@RequestParam(defaultValue = "asc") String sort) {
        return flightService.getAllFlights(sort);
    }

    @GetMapping("/{id}")
    public FlightDTO getFlightById(@PathVariable String id) {
        return flightService.getFlightById(id);
    }

    @GetMapping("/{id}/schedules")
    public List<FlightDTO> getFlightSchedules(@RequestParam LocalDate dates) {
        return flightService.getFlightsByDate(dates);
    }

    @PostMapping
    public String addFlight(@RequestBody FlightDTO flight) {
        flightService.addFlight(flight);
        return "Flight added successfully";
    }

    // Exception Handling
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
