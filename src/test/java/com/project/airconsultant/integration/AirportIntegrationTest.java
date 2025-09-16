package com.project.airconsultant.integration;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.service.AirportService;
import com.project.airconsultant.unit.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AirportIntegrationTest {

    @Autowired
    private AirportService airportService;

    @Test
    public void shouldFetchAirportByIcaoSuccessfully() {
        Airport airportResponse = this.airportService.getAirport("KJFK");

        assertEquals(airportResponse, TestUtils.airportServiceGetAirportMockObject());
    }

    @Test
    public void shouldThrowNotFoundForAnInvalidAirportIcao() {
        assertThrows(ResponseStatusException.class, () -> this.airportService.getAirport("INVALID"));
    }
}
