package com.project.airconsultant.service;

import com.project.airconsultant.client.AviationClient;
import com.project.airconsultant.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;


@Service
public class AirportService implements IAirportService {
    private final AviationClient aviationClient;

    @Autowired
    public AirportService(AviationClient aviationClient) {
        this.aviationClient = aviationClient;
    }

    public Airport getAirport(String icaoParam) {
        String airportIcao = icaoParam.toUpperCase();
        
        Map<String, List<Airport>> airportResponse = aviationClient.getAirportByIcao(airportIcao);
        if (airportResponse.get(airportIcao) != null && !airportResponse.get(airportIcao).isEmpty()) {
            return airportResponse.get(airportIcao).get(0);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found.");
        }
    }
}
