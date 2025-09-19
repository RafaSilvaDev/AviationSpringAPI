package com.project.airconsultant.config;

import com.project.airconsultant.repository.ICacheDataRepository;
import com.project.airconsultant.service.cache.CacheDataService;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@EnableRetry
@EnableCaching
public class WebConfig {

    @Autowired
    private ICacheDataRepository cacheDataRepository;

    @Bean
    public RestTemplate restTemplate() {
        HttpClientBuilder clientBuilder = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectionRequestTimeout(Timeout.of(Duration.ofSeconds(5L)))
                        .setResponseTimeout(Timeout.of(Duration.ofSeconds(10L)))
                        .build());
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(clientBuilder.build()));
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(2000);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        return retryTemplate;
    }

    @Bean
    public CacheManager cacheManager() {
        return new SimpleCacheManager() {
            @Override
            public Cache getCache(String name) {
                return new CacheDataService(cacheDataRepository, name);
            }
        };
    }
}
