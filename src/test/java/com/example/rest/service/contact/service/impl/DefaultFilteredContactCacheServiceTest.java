package com.example.rest.service.contact.service.impl;

import com.example.rest.service.contact.config.TestConf;
import com.example.rest.service.contact.repository.ContactRepository;
import com.example.rest.service.contact.service.ContactFilterService;
import com.example.rest.service.contact.service.FilteredContactCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConf.class})
public class DefaultFilteredContactCacheServiceTest {
    @MockBean
    private ContactRepository repo;

    @MockBean
    private ContactFilterService mock;

    @Autowired
    private FilteredContactCacheService service;

    @Test
    public void getContacts() {
        service.getContacts("");
        service.getContacts("");

        Mockito.verify(mock, Mockito.times(1)).getContacts("");
    }
}