package com.project.airconsultant.controller;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/{icao}")
    public Airport getAirportByIcao(@PathVariable(value = "icao") String icao) {
        return airportService.getAirport(icao);
    }
}
