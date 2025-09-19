package com.project.airconsultant.integration;

import com.project.airconsultant.model.AviationAirport;
import com.project.airconsultant.service.AirportService;
import com.project.airconsultant.service.AviationAirportService;
import com.project.airconsultant.unit.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AviationAirportIntegrationTest {

    @Autowired
    private AirportService airportService;

    @Autowired
    private AviationAirportService aviationAirportService;

    @Test
    public void shouldFetchAirportByIcaoSuccessfully() {
        AviationAirport aviationAirportResponse = this.aviationAirportService.findByIcao("KJFK");

        assertEquals(aviationAirportResponse, TestUtils.aviationAirportServiceGetAviationAirportMockObject());
    }

    @Test
    public void shouldThrowNotFoundForAnInvalidAirportIcao() {
        assertThrows(ResponseStatusException.class, () -> this.airportService.findByIcao("INVALID"));
    }
}
