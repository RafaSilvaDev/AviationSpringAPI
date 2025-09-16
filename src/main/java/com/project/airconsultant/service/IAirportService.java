package com.project.airconsultant.service;

import com.project.airconsultant.model.Airport;

public interface IAirportService {
    Airport getAirport(String icaoParam);
}
