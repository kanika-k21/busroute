package com.javaws.busroute.dto;

import com.javaws.busroute.model.BusType;
import lombok.Data;

import java.util.List;

@Data
public class BusDTO {
    private Long busId;
    private String registrationNumber;
    private BusType busType;
    private List<ScheduleDTO> schedules;

    public BusDTO(Long busId, String registrationNumber, BusType busType, List<ScheduleDTO> schedules) {
        this.busId = busId;
        this.registrationNumber = registrationNumber;
        this.busType = busType;
        this.schedules = schedules;
    }
}