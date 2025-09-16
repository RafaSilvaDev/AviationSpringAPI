package com.project.airconsultant.unit.service;

import com.project.airconsultant.client.AviationClient;
import com.project.airconsultant.model.Airport;
import com.project.airconsultant.service.AirportService;
import com.project.airconsultant.unit.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @InjectMocks
    private AirportService airportService;

    @Mock
    private AviationClient aviationClient;

    @Test
    void shouldReturnAirportByIcao() {
        when(aviationClient.getAirportByIcao("KJFK")).thenReturn(Collections.singletonMap("KJFK", Collections.singletonList(TestUtils.airportServiceGetAirportMockObject())));
        Airport airport = airportService.getAirport("KJFK");
        assertEquals(airport, TestUtils.airportServiceGetAirportMockObject());
    }
}