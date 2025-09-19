package com.project.airconsultant.repository;

import com.project.airconsultant.model.CacheData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICacheDataRepository extends JpaRepository<CacheData, Long> {
    @Query("SELECT c.cacheValue FROM cache_data c WHERE c.cacheName = ?1 AND c.cacheKey = ?2")
    byte[] findCacheValueByNameAndKey(String cacheN, String cacheK);

    @Query("SELECT c.id, c.cacheValue, c.cacheName, c.cacheKey, c.createdAt FROM cache_data c WHERE c.cacheKey = ?1")
    CacheData findCacheDataByCacheKey(String cacheK);

    @Modifying
    @Transactional
    @Query("UPDATE cache_data c SET c.cacheValue = ?3 WHERE c.cacheName = ?1 AND c.cacheKey = ?2")
    void putCacheData(String cacheN, String cacheK, byte[] cacheV);

    @Modifying
    @Transactional
    @Query("DELETE FROM cache_data c WHERE c.cacheName = ?1 AND c.cacheKey = ?2")
    void deleteCacheByNameAndKey(String cacheN, String cacheK);

    @Modifying
    @Transactional
    @Query("DELETE FROM cache_data c WHERE c.cacheName = ?1")
    void clearCacheDataByCacheName(String cacheN);
}
