package com.project.airconsultant.unit.service;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.repository.IAirportRepository;
import com.project.airconsultant.service.AirportService;
import com.project.airconsultant.unit.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @InjectMocks
    private AirportService airportService;

    @Mock
    private IAirportRepository airportRepository;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Random random;

    @Test
    void storeAirport() {

    }

    @Test
    void shouldReturnAirportByIcao() {
        when(airportRepository.findAirportByIcaoCode("KJFK")).thenReturn(TestUtils.airportServiceGetAirportMockObject());
        Airport airport = airportService.findByIcao("KJFK");
        assertEquals(airport, TestUtils.airportServiceGetAirportMockObject());
    }

    @Test
    void shouldReturnAirportByIdWithMethodQuery() {
        when(random.nextInt(4)).thenReturn(0);
        when(airportRepository.findById(10L)).thenReturn(Optional.of(TestUtils.airportServiceGetAirportMockObject()));
        Airport airport = airportService.findById(10L);

        assertEquals(airport, TestUtils.airportServiceGetAirportMockObject());
    }
    @Test
    void shouldReturnAirportByIdWithHQL() {
        when(random.nextInt(4)).thenReturn(1);
        when(airportRepository.findByIdWithPlanesHQL(10L)).thenReturn(Optional.of(TestUtils.airportServiceGetAirportMockObject()));
        Airport airport = airportService.findById(10L);

        assertEquals(airport, TestUtils.airportServiceGetAirportMockObject());
    }
    @Test
    void shouldReturnAirportByIdWithCriteria() {
        when(random.nextInt(4)).thenReturn(2);
        when(airportRepository.findAirportById(10L)).thenReturn(Optional.of(TestUtils.airportServiceGetAirportMockObject()));
        Airport airport = airportService.findById(10L);

        assertEquals(airport, TestUtils.airportServiceGetAirportMockObject());
    }
    @Test
    void shouldReturnAirportByIdWithNativeQuery() {
        when(random.nextInt(4)).thenReturn(3);
        when(airportRepository.findByIdWithPlanesNative(10L)).thenReturn(TestUtils.airportServiceGetAirportNativeQueryMockObject());
        Airport airport = airportService.findById(10L);

        assertEquals(airport, TestUtils.airportServiceGetAirportMockObject());
    }
}