package com.project.airconsultant.repository;

import com.project.airconsultant.model.Airport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AirportRepositoryImpl implements AirportRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<Airport> findAirportById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Airport> query = criteriaBuilder.createQuery(Airport.class);

        Root<Airport> airportRoot = query.from(Airport.class);
        airportRoot.fetch("planes", JoinType.LEFT); //join LEFT == join.FETCH

        query.select(airportRoot).where(criteriaBuilder.equal(airportRoot.get("id"), id));

        TypedQuery<Airport> typedQuery = entityManager.createQuery(query);

        try {
        return Optional.of(typedQuery.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
