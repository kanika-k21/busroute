package com.javaws.busroute.repository;

import com.javaws.busroute.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByRegistrationNumber(String registrationNumber);
}
