package com.example.Planner.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
//@EnableScheduling
public class CacheService {

    @Autowired
    private CacheManager cacheManager;

    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Scheduled(fixedRate = 10000)
    public void evictAlliancesAtIntervals() {
        evictAllCaches();
    }

}
