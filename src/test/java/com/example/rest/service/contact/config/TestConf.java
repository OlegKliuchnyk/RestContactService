package com.example.rest.service.contact.config;

import com.example.rest.service.contact.repository.ContactRepository;
import com.example.rest.service.contact.service.ContactCacheService;
import com.example.rest.service.contact.service.ContactFilterService;
import com.example.rest.service.contact.service.FilteredContactCacheService;
import com.example.rest.service.contact.service.impl.DefaultContactCacheService;
import com.example.rest.service.contact.service.impl.DefaultFilterService;
import com.example.rest.service.contact.service.impl.DefaultFilteredContactCacheService;
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
    public ContactCacheService defaultContactCacheService(ContactRepository contactRepository) {
        return new DefaultContactCacheService(contactRepository);
    }

    @Bean
    public ContactFilterService ContactFilterService(ContactCacheService contactCacheService) {
        return new DefaultFilterService(contactCacheService);
    }

    @Bean
    public FilteredContactCacheService filteredContactCacheService(ContactFilterService contactFilterService) {
        return new DefaultFilteredContactCacheService(contactFilterService);
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("contacts", "filtered_contacts");
    }

}