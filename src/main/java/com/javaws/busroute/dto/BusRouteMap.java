package com.javaws.busroute.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Time;

@Data
public class BusRouteMap {
    @NotBlank(message = "Registration number cannot be blank")
    @Size(min = 10, max = 13, message = "Registration number must be between 10 and 13 characters")
    private String registrationNumber;

    @NotBlank(message = "origin cannot be blank")
    private String origin;

    @NotBlank(message = "destination cannot be blank")
    private String destination;

    @NotNull(message = "Start time cannot be null")
    private Time startTime;

    @NotNull(message = "End time cannot be null")
    private Time endTime;
}
