package com.project.airconsultant.repository;

import com.project.airconsultant.model.Airport;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAirportRepository extends JpaRepository<Airport, Long>, AirportRepositoryCustom {
    @Query("SELECT a FROM airport a JOIN FETCH a.planes WHERE a.icaoCode = ?1")
    Airport findAirportByIcaoCode(String icaoCode);

    @EntityGraph(value = "airport-with-planes", type = EntityGraph.EntityGraphType.FETCH) //forcing fetch eager
    Optional<Airport> findById(Long id);

    @Query("SELECT a FROM airport a JOIN FETCH a.planes WHERE a.id = :id")
    Optional<Airport> findByIdWithPlanesHQL(Long id);

    @Query(value = """
    SELECT a.*, p.*
    FROM airport a
    LEFT JOIN airport_planes ap ON a.id = ap.airport_id
    LEFT JOIN plane p ON p.id=ap.planes_id
    WHERE a.id = :id
    """, nativeQuery = true)
    List<Object[]> findByIdWithPlanesNative(@Param("id") Long id);
}
