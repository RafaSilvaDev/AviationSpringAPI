package com.project.airconsultant.client;

import com.project.airconsultant.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Component
public class AviationClient implements IAviationClient {
    private static final String BASE_URL = "https://api.aviationapi.com/v1";
    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    @Autowired
    public AviationClient(RestTemplate restTemplate, RetryTemplate retryTemplate) {
        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
    }

    @Override
    public Map<String, List<Airport>> getAirportByIcao(String airportIcao) {
        String url = UriComponentsBuilder.fromUriString(BASE_URL + "/airports")
                .queryParam("apt", airportIcao)
                .toUriString();
        try {
            return retryTemplate.execute(context -> restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, List<Airport>>>() {
                    }
            ).getBody());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Unable to look up for airport from Aviation due to: " + e.getMessage()
            );
        }
    }
}