package com.project.airconsultant.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.airconsultant.controller.AirportController;
import com.project.airconsultant.service.AirportService;
import com.project.airconsultant.unit.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirportControllerTest {
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
    void shouldGetAirportByIcaoSuccessfully() throws Exception {
        when(airportService.findByIcao("KJFK")).thenReturn(TestUtils.airportServiceGetAirportMockObject());

        mockMvc.perform(MockMvcRequestBuilders.get("/airports/KJFK")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.icao_ident").value("KJFK"));

        verify(airportService, times(1)).findByIcao("KJFK");
    }

    @Test
    void shouldCreateAirportSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/airports").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(TestUtils.airportServiceGetAirportMockObject()))).andExpect(MockMvcResultMatchers.status().isCreated());

        verify(airportService, times(1)).storeAirport(TestUtils.airportServiceGetAirportMockObject());
    }
}