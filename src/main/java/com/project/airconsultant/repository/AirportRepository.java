package com.project.airconsultant.repository;

import com.project.airconsultant.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query("SELECT a FROM airport a WHERE a.icaoCode = ?1")
    Airport findAirportByIcaoCode(String icaoCode);
}
