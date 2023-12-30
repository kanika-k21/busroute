package com.javaws.busroute.service;

import com.javaws.busroute.model.Route;

public interface RouteService {
    Route fetchRouteByOriginAndDestination(String origin, String destination);
}
