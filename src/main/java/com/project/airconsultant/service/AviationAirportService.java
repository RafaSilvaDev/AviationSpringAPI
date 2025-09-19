package com.project.airconsultant.service;

import com.project.airconsultant.client.AviationClient;
import com.project.airconsultant.model.AviationAirport;
import com.project.airconsultant.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AviationAirportService implements IAviationAirportService {
    private final AviationClient aviationClient;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    public AviationAirportService(AviationClient aviationClient) {
        this.aviationClient = aviationClient;
    }

    @Override
    @Cacheable(value = Constants.AVIATION_AIRPORTS_ENDPOINT_VALUE, key = "#icaoParam")
    public AviationAirport findByIcao(String icaoParam) {
        String aviationAirport = icaoParam.toUpperCase();

        simulateSlowService();
        Map<String, List<AviationAirport>> aviationAirportResponse = aviationClient.getAirportByIcao(aviationAirport);
        if (aviationAirportResponse.get(aviationAirport) != null && !aviationAirportResponse.get(aviationAirport).isEmpty()) {
            return aviationAirportResponse.get(aviationAirport).get(0);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found.");
        }
    }

    @Override
    @CacheEvict(Constants.AVIATION_AIRPORTS_ENDPOINT_VALUE)
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
