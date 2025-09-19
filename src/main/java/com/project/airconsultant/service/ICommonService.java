package com.project.airconsultant.service;

public interface ICommonService<T, ID> {
    public T findByIcao(String icao);
    public void evictAllCacheValues(String cacheName);
}
