package com.javaws.busroute.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "buses")
@Data
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bus_sequence")
    @SequenceGenerator(name = "bus_sequence", sequenceName = "bus_sequence", initialValue = 1)
    private Long id;
    private String registrationNumber;
    private BusType busType;
    @OneToMany(mappedBy = "bus")
    @JsonIgnoreProperties("bus")
    Set<BusRouteSchedule> busRouteSchedules;

    public Bus() {}
    public Bus(String registrationNumber, BusType busType) {
        this.registrationNumber = registrationNumber;
        this.busType = busType;
    }
}
