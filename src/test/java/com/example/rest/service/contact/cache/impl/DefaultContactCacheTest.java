package com.example.rest.service.contact.cache.impl;

import com.example.rest.service.contact.config.TestConf;
import com.example.rest.service.contact.repository.ContactRepository;
import com.example.rest.service.contact.cache.ContactCache;
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
public class DefaultContactCacheTest {

    @MockBean
    private ContactRepository contactRepository;

    @Autowired
    private ContactCache service;

    @Test
    public void getAllContacts() {
        service.getAllContacts();
        service.getAllContacts();
        service.getAllContacts();
        service.getAllContacts();

        Mockito.verify(contactRepository, Mockito.times(2)).findAll();
    }
}