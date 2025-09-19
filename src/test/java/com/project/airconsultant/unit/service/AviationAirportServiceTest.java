package com.project.airconsultant.unit.service;

import com.project.airconsultant.client.AviationClient;
import com.project.airconsultant.model.AviationAirport;
import com.project.airconsultant.service.AviationAirportService;
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
class AviationAirportServiceTest {

    @InjectMocks
    private AviationAirportService aviationAirportService;

    @Mock
    private AviationClient aviationClient;

    @Test
    void shouldReturnAirportByIcao() {
        when(aviationClient.getAirportByIcao("KJFK")).thenReturn(Collections.singletonMap("KJFK", Collections.singletonList(TestUtils.aviationAirportServiceGetAviationAirportMockObject())));
        AviationAirport aviationAirport = aviationAirportService.findByIcao("KJFK");
        assertEquals(aviationAirport, TestUtils.aviationAirportServiceGetAviationAirportMockObject());
    }
}