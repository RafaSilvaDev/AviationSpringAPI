package com.project.airconsultant.controller;

import com.project.airconsultant.model.AviationAirport;
import com.project.airconsultant.service.AviationAirportService;
import com.project.airconsultant.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/" + Constants.AVIATION_AIRPORTS_ENDPOINT_VALUE)
public class AviationAirportController {
    @Autowired
    private AviationAirportService aviationAirportService;

    @GetMapping("/{icao}")
    @ResponseStatus(HttpStatus.OK)
    public AviationAirport getAirportByIcao(@PathVariable(value = "icao") String icao) {
        return aviationAirportService.findByIcao(icao);
    }

    @PostMapping("/cache")
    public void clearAirportsCache() {
        aviationAirportService.evictAllCacheValues(Constants.AVIATION_AIRPORTS_ENDPOINT_VALUE);
    }
}
