package com.axxes.cc.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cc.cache")
public class CacheProperties {

    private long clearInterval;

    public long getClearInterval() {
        return clearInterval;
    }

    public void setClearInterval(long clearInterval) {
        this.clearInterval = clearInterval;
    }

}