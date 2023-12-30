package com.javaws.busroute.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Entity
@Table(name = "bus_route_schedules")
@Data
public class BusRouteSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bus_route_schedule_sequence")
    @SequenceGenerator(name = "bus_route_schedule_sequence", sequenceName = "bus_route_schedule_sequence", initialValue = 1)
    private Long id;
    private Time startTime;
    private Time endTime;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public BusRouteSchedule() {}
    public BusRouteSchedule(Time startTime, Time endTime, Bus bus, Route route) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.bus = bus;
        this.route = route;
    }
}
