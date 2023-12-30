package com.javaws.busroute.repository;

import com.javaws.busroute.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    boolean existsByOriginAndDestination(String origin, String destination);
    Route findByOriginAndDestination(String origin, String destination);
}
