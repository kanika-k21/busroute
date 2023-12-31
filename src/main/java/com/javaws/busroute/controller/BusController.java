package com.javaws.busroute.controller;

import com.javaws.busroute.dto.*;
import com.javaws.busroute.exception.BadRequestException;
import com.javaws.busroute.model.Bus;
import com.javaws.busroute.model.BusRouteSchedule;
import com.javaws.busroute.model.Route;
import com.javaws.busroute.service.BusRouteScheduleService;
import com.javaws.busroute.service.BusService;
import com.javaws.busroute.service.RouteService;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class BusController {
    @Autowired
    private BusService busService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private BusRouteScheduleService busRouteScheduleService;

    @Autowired
    private Validator validator;

    @PostMapping("/addBus")
    public ResponseEntity<BusDTO> addBus(@RequestBody BusDTO busDTO) {
        Set<ConstraintViolation<BusDTO>> violations = validator.validate(busDTO);
        if (!violations.isEmpty()) {
            throw new BadRequestException(violations.iterator().next().getMessage());
        }

        Bus bus = busService.addBusDetails(busDTO);
        BusDTO finalBusDTO = new BusDTO(bus.getId(), bus.getRegistrationNumber(), bus.getBusType(), List.of(new ScheduleDTO()));
        return new ResponseEntity<>(finalBusDTO, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/buses/{registrationNumber}")
    public ResponseEntity<BusDTO> getBusDetails(@PathVariable("registrationNumber") String registrationNumber) {
        Bus bus = busService.fetchBusByRegistrationNumber(registrationNumber);
        List<ScheduleDTO> busRouteSchedules = busRouteScheduleService.findRouteSchedulesByBusId(bus.getId());

        BusDTO busDTO =  new BusDTO(bus.getId(), bus.getRegistrationNumber(), bus.getBusType(), busRouteSchedules);
        return new ResponseEntity<>(busDTO, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/deleteBus")
    public ResponseEntity<?> deleteBus(@RequestBody BusDTO busDTO) {
        Set<ConstraintViolation<BusDTO>> violations = validator.validate(busDTO);
        if (!violations.isEmpty()) {
            throw new BadRequestException(violations.iterator().next().getMessage());
        }

        busService.deleteBusDetails(busDTO);

        return ResponseEntity.ok().body(Map.of("message", "Bus details deleted successfully"));
    }

    @PutMapping("/mapBusWithRoute")
    public ResponseEntity<BusDTO> mapBusWithRoute(@RequestBody BusRouteMap busRouteMap) {
        Set<ConstraintViolation<BusRouteMap>> violations = validator.validate(busRouteMap);
        if (!violations.isEmpty()) {
            throw new BadRequestException(violations.iterator().next().getMessage());
        }

        Bus bus = busService.fetchBusByRegistrationNumber(busRouteMap.getRegistrationNumber());

//      checking if the current bus has any overlapping schedule
        busService.checkForOverlappingSchedule(bus, busRouteMap.getStartTime(), busRouteMap.getEndTime());
        Route route = routeService.fetchRouteByOriginAndDestination(busRouteMap.getOrigin(), busRouteMap.getDestination());
        BusRouteSchedule busRouteSchedule = busRouteScheduleService.mapBusWithRoute(bus, route, busRouteMap);

        RouteDTO routeDTO = new RouteDTO(route.getOrigin(), route.getDestination());
        TimingDTO timingDTO = new TimingDTO(busRouteSchedule.getStartTime(), busRouteSchedule.getEndTime());
        ScheduleDTO scheduleDTO =  new ScheduleDTO(routeDTO, timingDTO);
        BusDTO busDTO = new BusDTO(bus.getId(), bus.getRegistrationNumber(), bus.getBusType(), List.of(scheduleDTO));
        return new ResponseEntity<>(busDTO, HttpStatus.CREATED);
    }
}
