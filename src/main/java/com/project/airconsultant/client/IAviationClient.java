package com.project.airconsultant.client;

import com.project.airconsultant.model.Airport;

import java.util.List;
import java.util.Map;

public interface IAviationClient {
    Map<String, List<Airport>> getAirportByIcao(String airportIcao);
}
