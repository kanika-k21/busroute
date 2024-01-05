package com.javaws.busroute.service;

import com.javaws.busroute.dto.BusDTO;
import com.javaws.busroute.exception.ResourceNotFoundException;
import com.javaws.busroute.exception.BadRequestException;
import com.javaws.busroute.model.Bus;
import com.javaws.busroute.model.BusRouteSchedule;
import com.javaws.busroute.repository.BusRepository;
import com.javaws.busroute.repository.BusRouteScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Override
    public void deleteBusDetails(BusDTO busDTO) {
        Bus bus = this.fetchBusByRegistrationNumber(busDTO.getRegistrationNumber());
        busRepository.delete(bus);
    }

    @Override
    public Bus fetchBusByRegistrationNumber(String registrationNumber) {
        Optional<Bus> bus = busRepository.findByRegistrationNumber(registrationNumber);

        return bus.orElseThrow(() -> new ResourceNotFoundException("No bus present with the given registrationNumber - " + registrationNumber));
    }

    @Override
    public void checkForOverlappingSchedule(Bus bus, Time startTime, Time endTime) {
        List<BusRouteSchedule> busRouteSchedules = busRouteScheduleRepository.findByBusId(bus.getId());
        boolean overlapped;
        for (BusRouteSchedule busRouteSchedule : busRouteSchedules) {
            Time existingStartTime = busRouteSchedule.getStartTime();
            Time existingEndTime = busRouteSchedule.getEndTime();
//          to check conflict schedules if any
            if (!(endTime.before(existingStartTime) || startTime.after(existingEndTime))) {
               throw new BadRequestException("Provided bus has a conflicting schedule! Adjust the timings and reschedule!");
            }
        }
    }
}
