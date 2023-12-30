package com.javaws.busroute.repository;

import com.javaws.busroute.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsByRegistrationNumber(String registrationNumber);
    Bus findByRegistrationNumber(String registrationNumber);
}
