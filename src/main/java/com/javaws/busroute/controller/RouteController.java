package com.javaws.busroute.controller;

import com.javaws.busroute.dto.RouteDTO;
import com.javaws.busroute.model.Route;
import com.javaws.busroute.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping("/routes")
    public ResponseEntity<List<RouteDTO>> getRoutes() {
        List<Route> routes = routeService.findAllRoutes();
        List<RouteDTO> routeDTOS = new ArrayList<>();

        for (Route route : routes) {
            routeDTOS.add(new RouteDTO(route.getOrigin(), route.getDestination()));
        }
        return new ResponseEntity<>(routeDTOS, HttpStatus.OK);
    }
}
