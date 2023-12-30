package com.javaws.busroute.service;

import com.javaws.busroute.exception.ResourceNotFoundException;
import com.javaws.busroute.model.Route;
import com.javaws.busroute.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImplementation implements RouteService {

    @Autowired
    private RouteRepository routeRepository;
    public Route fetchRouteByOriginAndDestination(String origin, String destination) {
//      since routes are static preloaded data, checking if any route exists with the given origin and destination
        if (!routeRepository.existsByOriginAndDestination(origin, destination)) {
            throw new ResourceNotFoundException("No route found with the given origin and destination.");
        }
        return routeRepository.findByOriginAndDestination(origin, destination);
    }

}
