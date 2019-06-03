package com.example.rest.service.contact.config;

import com.example.rest.service.contact.repository.ContactRepository;
import com.example.rest.service.contact.cache.ContactCache;
import com.example.rest.service.contact.service.ContactFilterService;
import com.example.rest.service.contact.cache.impl.DefaultContactCache;
import com.example.rest.service.contact.service.impl.DefaultFilterService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("test")
@TestConfiguration
@EnableCaching
public class TestConf {

    @Bean
    public ContactCache defaultContactCacheService(ContactRepository contactRepository) {
        return new DefaultContactCache(contactRepository);
    }

    @Bean
    public ContactFilterService ContactFilterService(ContactCache contactCacheService) {
        return new DefaultFilterService(contactCacheService);
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("contacts", "filtered_contacts");
    }

}