package com.project.airconsultant.config;

import com.project.airconsultant.cache.DatabaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public CacheManager cacheManager() {
        return new SimpleCacheManager() {
            @Override
            public Cache getCache(String name) {
                return new DatabaseCache(jdbcTemplate, name);
            }
        };
    }
}

