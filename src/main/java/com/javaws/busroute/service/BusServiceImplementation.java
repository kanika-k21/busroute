package com.javaws.busroute.service;

import com.javaws.busroute.dto.BusDTO;
import com.javaws.busroute.exception.ResourceNotFoundException;
import com.javaws.busroute.exception.BadRequestException;
import com.javaws.busroute.model.Bus;
import com.javaws.busroute.model.BusRouteSchedule;
import com.javaws.busroute.repository.BusRepository;
import com.javaws.busroute.repository.BusRouteScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class BusServiceImplementation implements BusService {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private BusRouteScheduleRepository busRouteScheduleRepository;

    @Override
    public Bus addBusDetails(BusDTO busDTO) {
        Bus bus = new Bus(busDTO.getRegistrationNumber(), busDTO.getBusType());
        return busRepository.save(bus);
    }

    @Override
    public void deleteBusDetails(Bus bus) {
        busRepository.delete(bus);
    }

    @Override
    public Bus fetchBusByRegistrationNumber(String registrationNumber) {
        if (!busRepository.existsByRegistrationNumber(registrationNumber)) {
            throw new ResourceNotFoundException("No bus present with the given registrationNumber - " + registrationNumber);
        }
        return busRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public void checkForOverlappingSchedule(Bus bus, Time startTime, Time endTime) {
        List<BusRouteSchedule> busRouteSchedules = busRouteScheduleRepository.findByBusId(bus.getId());
        boolean overlapped;
        for (BusRouteSchedule busRouteSchedule : busRouteSchedules) {
            Time existingStartTime = busRouteSchedule.getStartTime();
            Time existingEndTime = busRouteSchedule.getEndTime();
            if (!(endTime.before(existingStartTime) || startTime.after(existingEndTime))) {
               throw new BadRequestException("Provided bus has a conflicting schedule! Adjust the timings and reschedule!");
            }
        }
    }
}
