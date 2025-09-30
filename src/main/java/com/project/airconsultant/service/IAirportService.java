package com.project.airconsultant.service;

import com.project.airconsultant.model.Airport;

public interface IAirportService extends ICommonService<Airport, Long>{
    void storeAirport(Airport airport);
    void deleteAirport(Long id);
    Airport findById(Long id);
}
