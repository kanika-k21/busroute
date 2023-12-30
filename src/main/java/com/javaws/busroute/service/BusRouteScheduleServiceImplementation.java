package com.javaws.busroute.service;

import com.javaws.busroute.dto.RouteDTO;
import com.javaws.busroute.dto.ScheduleDTO;
import com.javaws.busroute.dto.TimingDTO;
import com.javaws.busroute.model.Bus;
import com.javaws.busroute.dto.BusRouteMap;
import com.javaws.busroute.model.BusRouteSchedule;
import com.javaws.busroute.model.Route;
import com.javaws.busroute.repository.BusRouteScheduleRepository;
import com.javaws.busroute.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusRouteScheduleServiceImplementation implements BusRouteScheduleService {

    @Autowired
    private BusRouteScheduleRepository busRouteScheduleRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Override
    public BusRouteSchedule mapBusWithRoute(Bus bus, Route route, BusRouteMap busRouteMap) {
        BusRouteSchedule busRouteSchedule = new BusRouteSchedule(busRouteMap.getStartTime(), busRouteMap.getEndTime(), bus, route);
        return busRouteScheduleRepository.save(busRouteSchedule);
    }

    @Override
    public List<ScheduleDTO> findRouteSchedulesByBusId(Long busId) {
        List<BusRouteSchedule> busRouteSchedules = busRouteScheduleRepository.findByBusId(busId);
        return this.constructScheduleDTOs(busRouteSchedules);
    }

    private List<ScheduleDTO> constructScheduleDTOs(List<BusRouteSchedule> busRouteSchedules) {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        for (BusRouteSchedule busRouteSchedule : busRouteSchedules) {
            RouteDTO routeDTO = new RouteDTO(busRouteSchedule.getRoute().getOrigin(), busRouteSchedule.getRoute().getDestination());
            TimingDTO timingDTO = new TimingDTO(busRouteSchedule.getStartTime(), busRouteSchedule.getEndTime());
            ScheduleDTO scheduleDTO = new ScheduleDTO(routeDTO, timingDTO);
            scheduleDTOs.add(scheduleDTO);
        }
        return scheduleDTOs;
    }
}
