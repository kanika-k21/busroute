package com.javaws.busroute.repository;

import com.javaws.busroute.model.BusRouteSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRouteScheduleRepository extends JpaRepository<BusRouteSchedule, Long> {
    List<BusRouteSchedule> findByBusId(Long busId);
}
