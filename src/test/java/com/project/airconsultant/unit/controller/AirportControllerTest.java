package com.project.airconsultant.unit.controller;

import com.project.airconsultant.controller.AirportController;
import com.project.airconsultant.service.AirportService;
import com.project.airconsultant.unit.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.*;

public class AirportControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();
    }

    @Test
    public void testGetAirportByIcao_Success() throws Exception {
        when(airportService.getAirport("KJFK")).thenReturn(TestUtils.airportServiceGetAirportMockObject());

        mockMvc.perform(MockMvcRequestBuilders.get("/airports/KJFK"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.icao_ident").value("KJFK"));

        verify(airportService, times(1)).getAirport("KJFK");
    }

    @Test
    public void testGetAirportByIcao_NotFound() throws Exception {
        when(airportService.getAirport("INVALID")).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found."));

        mockMvc.perform(MockMvcRequestBuilders.get("/airports/INVALID"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(airportService, times(1)).getAirport("INVALID");
    }
}
