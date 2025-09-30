package com.project.airconsultant.service;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.model.Plane;
import com.project.airconsultant.repository.IAirportRepository;
import com.project.airconsultant.util.Constants;
import com.project.airconsultant.util.CustomMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@Service
public class AirportService implements IAirportService {
    @Autowired
    private Random random;

    @Autowired
    private IAirportRepository airportRepository;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void storeAirport(Airport airport) {
        String airportIcao = airport.getIcaoCode();
        Airport lookUpForAirport = airportRepository.findAirportByIcaoCode(airportIcao);
        if (lookUpForAirport == null) {
            airport.setPlanes(generateRandomPlanes());
            airportRepository.save(airport);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Airport with ICAO: " + airportIcao + " already exists in data source.");
        }

    }

    @Override
    public void deleteAirport(Long id) {
        Airport airportToBeDeleted = airportRepository.findById(id).orElse(null);
        if (airportToBeDeleted == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found.");
        else airportRepository.delete(airportToBeDeleted);
    }

    @Override
    @Cacheable(value = Constants.AIRPORTS_ENDPOINT_VALUE, key = "#icaoParam")
    public Airport findByIcao(String icaoParam) {
        String airportIcao = icaoParam.toUpperCase();

        simulateSlowService();
        Airport foundAirport = airportRepository.findAirportByIcaoCode(airportIcao);
        if (foundAirport == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found.");
        } else {
            return foundAirport;
        }
    }

    @Override
    @Cacheable(value = Constants.AIRPORTS_ENDPOINT_VALUE, key = "#id")
    public Airport findById(Long id) {
        simulateSlowService();
        int rand = random.nextInt(4) + 1;
        Optional<Airport> foundAirport = Optional.empty();
        switch (rand) {
            case 1:
                System.out.println("Fetching using Method Query");
                foundAirport = airportRepository.findById(id);
                break;
            case 2:
                System.out.println("Fetching using HQL");
                foundAirport = airportRepository.findByIdWithPlanesHQL(id);
                break;
            case 3:
                System.out.println("Fetching using Criteria");
                foundAirport = airportRepository.findAirportById(id);
                break;
            case 4:
                System.out.println("Fetching using Native Query");
                List<Object[]> foundAirportUsingNative = airportRepository.findByIdWithPlanesNative(id);
                Airport airport = null;
                List<Plane> planes = new ArrayList<>();

                for (Object[] row : foundAirportUsingNative) {
                    if (airport == null) {
                        airport = CustomMappers.mapAirportFromRows(row);
                    }

                    Plane plane = CustomMappers.mapPlaneFromRows(row);
                    if (plane != null && plane.getId() != null) {
                        planes.add(plane);
                    }
                }
                if (airport != null) {
                    airport.setPlanes(planes);
                    foundAirport = Optional.of(airport);
                }
                break;
        }

        if (foundAirport.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found.");
        } else {
            //forcing hibernate to load planes in case of Criteria, bypassing the lazy loading of airports without planes
            foundAirport.get().getPlanes().size();
            return foundAirport.get();
        }
    }

    @Override
    @CacheEvict(Constants.AIRPORTS_ENDPOINT_VALUE)
    public void evictAllCacheValues(String cacheName) {
        Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Plane> generateRandomPlanes() {
        List<Plane> planes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Plane plane = new Plane();
            plane.setCode(String.valueOf(i));
            plane.setModel("Model " + i);
            planes.add(plane);
        }
        return planes;
    }
}
