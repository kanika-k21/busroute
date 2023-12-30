package com.javaws.busroute.dto;

import lombok.Data;

import java.sql.Time;

@Data
public class BusRouteMap {
    private String registrationNumber;
    private String origin;
    private String destination;
    private Time startTime;
    private Time endTime;
}
