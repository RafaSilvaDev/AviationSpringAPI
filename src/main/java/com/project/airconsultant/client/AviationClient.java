package com.project.airconsultant.client;

import com.project.airconsultant.model.AviationAirport;
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
    public Map<String, List<AviationAirport>> getAirportByIcao(String airportIcao) {
        String url = UriComponentsBuilder.fromUriString(BASE_URL + "/airports")
                .queryParam("apt", airportIcao)
                .toUriString();
        try {
            return retryTemplate.execute(context -> restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, List<AviationAirport>>>() {
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


/*
* DONE banco postgres 15/16 -> docker
* DONE tabela cache banco -> verificar requests
* DONE verificar se o retorno da API veio de cache (POST e GET)
* DONE WITH METHOD QUERY method query, hql, criteria e native query (spring data)
* DONE duplicar endpoint para receber um objeto e validar o mesmo com Bean validation -> POST
* DONE repository, service e controller com generics
* */