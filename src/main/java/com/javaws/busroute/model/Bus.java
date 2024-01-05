package com.javaws.busroute.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Column(unique = true, nullable = false)
    private String registrationNumber;

    @NotNull
    private BusType busType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bus", cascade = CascadeType.ALL)
    private Set<BusRouteSchedule> busRouteSchedules;

    public Bus() {}
    public Bus(String registrationNumber, BusType busType) {
        this.registrationNumber = registrationNumber;
        this.busType = busType;
    }
}
