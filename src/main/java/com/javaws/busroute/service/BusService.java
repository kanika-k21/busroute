package com.javaws.busroute.service;

import com.javaws.busroute.dto.BusDTO;
import com.javaws.busroute.model.Bus;

import java.sql.Time;

public interface BusService {
    Bus addBusDetails(BusDTO busDTO);
    void deleteBusDetails(BusDTO busDTO);
    Bus fetchBusByRegistrationNumber(String registrationNumber);
    void checkForOverlappingSchedule(Bus bus, Time startTime, Time endTime);
}
