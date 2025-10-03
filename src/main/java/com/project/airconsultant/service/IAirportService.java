package com.project.airconsultant.service;

import com.project.airconsultant.model.Airport;

public interface IAirportService extends ICommonService<Airport, Long>{
    Airport storeAirport(Airport airport);
    void deleteAirport(Long id);
    Airport findById(Long id);
}
