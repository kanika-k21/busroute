package com.javaws.busroute.service;

import com.javaws.busroute.dto.ScheduleDTO;
import com.javaws.busroute.model.Bus;
import com.javaws.busroute.dto.BusRouteMap;
import com.javaws.busroute.model.BusRouteSchedule;
import com.javaws.busroute.model.Route;

import java.util.List;

public interface BusRouteScheduleService {
    BusRouteSchedule mapBusWithRoute(Bus bus, Route route, BusRouteMap busRouteMap);
    List<ScheduleDTO> findRouteSchedulesByBusId(Long busId);
}
