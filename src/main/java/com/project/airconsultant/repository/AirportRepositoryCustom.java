package com.project.airconsultant.repository;

import com.project.airconsultant.model.Airport;

import java.util.Optional;

public interface AirportRepositoryCustom {
    Optional<Airport> findAirportById(Long id);
}
