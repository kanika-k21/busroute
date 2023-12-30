package com.javaws.busroute.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "routes")
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_sequence")
    @SequenceGenerator(name = "route_sequence", sequenceName = "route_sequence", initialValue = 1)
    private Long id;
    private String origin;
    private String destination;

    @OneToMany(mappedBy = "route")
    Set<BusRouteSchedule> busRouteSchedules;

    public Route() {};
    public Route(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }
}
