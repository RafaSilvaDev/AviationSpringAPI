package com.project.airconsultant.unit.client;

import com.project.airconsultant.client.AviationClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AviationClientTest {

    @InjectMocks
    private AviationClient aviationClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RetryTemplate retryTemplate;

    @Test
    public void shouldThrowResponseStatusException_whenRestTemplateThrowsException() {
        String airportIcao = "KJFK";

        when(retryTemplate.execute(context -> {
            throw new RuntimeException("Test exception");
        })).thenThrow(new RuntimeException("Test exception"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            aviationClient.getAirportByIcao(airportIcao);
        });

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, exception.getStatusCode());
    }
}
