package com.axxes.cc.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.google.common.cache.Cache;

@Configuration
@AutoConfigureAfter({ JmxAutoConfiguration.class,
        org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration.class })
@ConditionalOnClass(Cache.class)
@EnableConfigurationProperties(CacheProperties.class)
public class CacheAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheAutoConfiguration.class);

    @Bean
    @ConditionalOnBean(GuavaCacheManager.class)
    public GuavaCacheJmxBean guavaCacheJmxBean(GuavaCacheManager guavaCacheManager) {
        LOGGER.info("creating jmx accessor bean");
        return new GuavaCacheJmxBean(guavaCacheManager);
    }

    @Bean
    @ConditionalOnBean(GuavaCacheJmxBean.class)
    @ConditionalOnProperty(name = "cc.cache.clear-interval")
    public TaskExecutor cacheTaskExecutor(GuavaCacheJmxBean guavaCacheJmxBean, CacheProperties cacheProperties) {
        ThreadPoolTaskScheduler threadPoolTaskExecutor = new ThreadPoolTaskScheduler();
        threadPoolTaskExecutor.initialize();
        
        threadPoolTaskExecutor.scheduleWithFixedDelay(() -> {
            guavaCacheJmxBean.resetAllCaches();
        }, cacheProperties.getClearInterval());

        return threadPoolTaskExecutor;
    }

}
