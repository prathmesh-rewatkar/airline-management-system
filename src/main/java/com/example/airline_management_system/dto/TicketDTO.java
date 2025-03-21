package com.example.airline_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private String id;
    private String passengerName;
    private String email;
    private String phoneNumber;
    private String flightNumber;
    private String departureDate;
    private String seatNumber;
    private String status;
}
