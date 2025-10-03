package com.project.airconsultant.unit.service;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.repository.IAirportRepository;
import com.project.airconsultant.service.AirportService;
import com.project.airconsultant.unit.testutils.TestUtils;
import com.project.airconsultant.util.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @InjectMocks
    private AirportService airportService;

    @Mock
    private IAirportRepository airportRepository;

    @Mock
    private Random random;

    @Test
    void shouldStoreAirport() {
        when(airportRepository.save(any(Airport.class))).thenReturn(TestUtils.airportServiceGetAirportMockObject());

        Airport airport = airportService.storeAirport(TestUtils.airportServiceSaveAirportMockObject());

        assertEquals(airport, TestUtils.airportServiceGetAirportMockObject());
    }

    @Test
    void shouldReturnAirportByIcao() {
        when(airportRepository.findAirportByIcaoCode("KJFK")).thenReturn(TestUtils.airportServiceGetAirportMockObject());
        Airport airport = airportService.findByIcao("KJFK");
        assertEquals(airport, TestUtils.airportServiceGetAirportMockObject());
    }

    @Test
    void shouldThrowNotFoundForInvalidIcao() {
        when(airportRepository.findAirportByIcaoCode("INVALID")).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found."));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            airportRepository.findAirportByIcaoCode("INVALID");
        });

        assertEquals("Airport not found.", exception.getReason());
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

    @Test
    void shouldThrowNotFoundForInvalidId() {
        when(random.nextInt(4)).thenReturn(1);
        when(airportRepository.findByIdWithPlanesHQL(5L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            airportService.findById(5L);
        });

        assertEquals("Airport not found.", exception.getReason());
    }
}