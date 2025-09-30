package com.project.airconsultant.controller;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.service.AirportService;
import com.project.airconsultant.util.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/" + Constants.AIRPORTS_ENDPOINT_VALUE)
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/{icao}")
    @ResponseStatus(HttpStatus.OK)
    public Airport getStoredAirportByIcao(@PathVariable(value = "icao") String icao) {
        return airportService.findByIcao(icao);
    }

    @GetMapping("/id/{id}")
    public Airport getStoredAirportById(@PathVariable(value = "id") Long id) {
        return airportService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void storeAirport(@RequestBody @Valid Airport airport) {
        airportService.storeAirport(airport);
    }

    @PostMapping("/cache")
    public void clearAirportsCache() {
        airportService.evictAllCacheValues(Constants.AIRPORTS_ENDPOINT_VALUE);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirport(@PathVariable(value = "id") Long id) {
        airportService.deleteAirport(id);
    }
}
