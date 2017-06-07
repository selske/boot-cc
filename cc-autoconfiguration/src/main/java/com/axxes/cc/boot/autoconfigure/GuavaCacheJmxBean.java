package com.axxes.cc.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource
public class GuavaCacheJmxBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaCacheJmxBean.class);
    
    private GuavaCacheManager guavaCacheManager;

    public GuavaCacheJmxBean(GuavaCacheManager guavaCacheManager) {
        this.guavaCacheManager = guavaCacheManager;
    }
    
    @ManagedOperation
    public void resetAllCaches() {
        LOGGER.info("clearing caches");
        guavaCacheManager.getCacheNames().forEach( cacheName -> {
            LOGGER.info("clearing cache {}", cacheName);
            guavaCacheManager.getCache(cacheName).clear();
        });
    }

}
