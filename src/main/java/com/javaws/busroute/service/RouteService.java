package com.javaws.busroute.service;

import com.javaws.busroute.model.Route;

import java.util.List;

public interface RouteService {
    Route fetchRouteByOriginAndDestination(String origin, String destination);
    List<Route> findAllRoutes();
}
