package com.project.airconsultant.client;

import com.project.airconsultant.model.AviationAirport;

import java.util.List;
import java.util.Map;

public interface IAviationClient {
    Map<String, List<AviationAirport>> getAirportByIcao(String airportIcao);
}
