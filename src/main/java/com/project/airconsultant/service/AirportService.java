package com.project.airconsultant.service;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.repository.AirportRepository;
import com.project.airconsultant.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


@Service
public class AirportService implements IAirportService {
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void storeAirport(Airport airport) {
        String airportIcao = airport.getIcaoCode();
        Airport lookUpForAirport = airportRepository.findAirportByIcaoCode(airportIcao);
        if(lookUpForAirport == null) {
            airportRepository.save(airport);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Airport with ICAO: " + airportIcao + " already exists in data source."
            );
        }

    }

    @Override
    @Cacheable(value = Constants.AIRPORTS_ENDPOINT_VALUE, key = "#icaoParam")
    public Airport findByIcao(String icaoParam) {
        String airportIcao = icaoParam.toUpperCase();

        simulateSlowService();
        Airport foundAirport = airportRepository.findAirportByIcaoCode(airportIcao);
        if (foundAirport == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Airport not found."
            );
        } else {
            return foundAirport;
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
}
