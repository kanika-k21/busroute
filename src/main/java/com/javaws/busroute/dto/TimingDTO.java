package com.javaws.busroute.dto;

import lombok.Data;

import java.sql.Time;

@Data
public class TimingDTO {
    private Time startTime;
    private Time endTime;

    public TimingDTO(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
