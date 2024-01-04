package com.javaws.busroute.dto;

import com.javaws.busroute.model.BusType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class BusDTO {
    private Long busId;

    @NotBlank(message = "Registration number cannot be blank")
    @Size(min = 10, max = 13, message = "Registration number must be between 10 and 13 characters")
    private String registrationNumber;
    private BusType busType;
    private List<ScheduleDTO> schedules;

    public BusDTO() {}

    public BusDTO(Long busId, String registrationNumber, BusType busType, List<ScheduleDTO> schedules) {
        this.busId = busId;
        this.registrationNumber = registrationNumber;
        this.busType = busType;
        this.schedules = schedules;
    }
}