package com.project.airconsultant.unit.controller;

import com.project.airconsultant.controller.AviationAirportController;
import com.project.airconsultant.service.AviationAirportService;
import com.project.airconsultant.unit.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AviationAirportControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AviationAirportService aviationAirportService;

    @InjectMocks
    private AviationAirportController aviationAirportController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(aviationAirportController).build();
    }

    @Test
    public void shouldGetAirportByIcaoSuccessfully() throws Exception {
        when(aviationAirportService.findByIcao("KJFK")).thenReturn(TestUtils.aviationAirportServiceGetAviationAirportMockObject());

        mockMvc.perform(MockMvcRequestBuilders.get("/aviation-airports/KJFK"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.icao_ident").value("KJFK"));

        verify(aviationAirportService, times(1)).findByIcao("KJFK");
    }

    @Test
    public void shouldThrowNotFoundForInvalidIcao() throws Exception {
        when(aviationAirportService.findByIcao("INVALID")).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found."));

        mockMvc.perform(MockMvcRequestBuilders.get("/aviation-airports/INVALID"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(aviationAirportService, times(1)).findByIcao("INVALID");
    }
}
