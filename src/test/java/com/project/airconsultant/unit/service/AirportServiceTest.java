package com.project.airconsultant.unit.service;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.repository.AirportRepository;
import com.project.airconsultant.service.AirportService;
import com.project.airconsultant.unit.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @InjectMocks
    private AirportService airportService;

    @Mock
    private AirportRepository airportRepository;

    @Test
    void storeAirport() {

    }

    @Test
    void shouldReturnAirportByIcao() {
        when(airportRepository.findAirportByIcaoCode("KJFK")).thenReturn(TestUtils.airportServiceGetAirportMockObject());
        Airport airport = airportService.findByIcao("KJFK");
        assertEquals(airport, TestUtils.airportServiceGetAirportMockObject());
    }
}