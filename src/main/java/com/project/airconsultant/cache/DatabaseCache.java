package com.project.airconsultant.cache;

import org.springframework.cache.Cache;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.SerializationUtils;

import java.util.List;
import java.util.concurrent.Callable;

public class DatabaseCache implements Cache {

    private final JdbcTemplate jdbcTemplate;
    private final String cacheName;

    public DatabaseCache(JdbcTemplate jdbcTemplate, String cacheName) {
        this.jdbcTemplate = jdbcTemplate;
        this.cacheName = cacheName;
    }

    @Override
    public String getName() {
        return cacheName;
    }

    @Override
    public Object getNativeCache() {
        return jdbcTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        String sql = "SELECT cache_value FROM cache_data WHERE cache_name = ? AND cache_key = ?";

        List<byte[]> result = jdbcTemplate.queryForList(sql, byte[].class, cacheName, key);

        if (result.isEmpty()) {
            return null;
        } else {
            byte[] serializedValue = result.get(0);
            Object value = SerializationUtils.deserialize(serializedValue);
            return () -> value;
        }
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        byte[] serializedValue = SerializationUtils.serialize(value);
        String sql = "INSERT INTO cache_data (cache_name, cache_key, cache_value) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, cacheName, key, serializedValue);
    }

    @Override
    public void evict(Object key) {
        String sql = "DELETE FROM cache_data WHERE cache_name = ? AND cache_key = ?";
        jdbcTemplate.update(sql, cacheName, key);
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM cache_data WHERE cache_name = ?";
        jdbcTemplate.update(sql, cacheName);
    }
}
