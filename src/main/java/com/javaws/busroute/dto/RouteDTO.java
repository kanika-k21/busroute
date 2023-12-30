package com.javaws.busroute.dto;

import lombok.Data;

@Data
public class RouteDTO {
    private String origin;
    private String destination;

    public RouteDTO(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }
}
