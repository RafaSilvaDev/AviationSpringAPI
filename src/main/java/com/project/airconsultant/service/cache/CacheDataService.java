package com.project.airconsultant.service.cache;

import com.project.airconsultant.model.CacheData;
import com.project.airconsultant.repository.ICacheDataRepository;
import org.springframework.cache.Cache;
import org.springframework.util.SerializationUtils;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class CacheDataService implements Cache {
    private final ICacheDataRepository cacheDataRepository;

    private final String cacheName;

    public CacheDataService(ICacheDataRepository cacheDataRepository, String cacheName) {
        this.cacheName = cacheName;
        this.cacheDataRepository = cacheDataRepository;
    }

    @Override
    public String getName() {
        return cacheName;
    }

    @Override
    public Object getNativeCache() {
        return cacheDataRepository;
    }

    @Override
    public ValueWrapper get(Object key) {
        byte[] cacheValue = cacheDataRepository.findCacheValueByNameAndKey(cacheName, key.toString());

        if(cacheValue == null) {
            return null;
        } else {
            Object value = SerializationUtils.deserialize(cacheValue);
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
        CacheData cacheData = cacheDataRepository.findCacheDataByCacheKey(key.toString());
        if(cacheData != null) {
            cacheDataRepository.putCacheData(cacheName, key.toString(), serializedValue);
        } else {
            CacheData newCacheData = new CacheData();
            newCacheData.setCacheValue(serializedValue);
            newCacheData.setCacheKey(key.toString());
            newCacheData.setCacheName(cacheName);
            newCacheData.setCreatedAt(LocalDateTime.now());
            cacheDataRepository.save(newCacheData);
        }
    }

    @Override
    public void evict(Object key) {
        cacheDataRepository.deleteCacheByNameAndKey(cacheName, key.toString());
    }

    @Override
    public void clear() {
        cacheDataRepository.clearCacheDataByCacheName(cacheName);
    }
}
