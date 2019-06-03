package com.example.rest.service.contact.service.impl;

import com.example.rest.service.contact.config.TestConf;
import com.example.rest.service.contact.dto.ContactDto;
import com.example.rest.service.contact.entity.Contact;
import com.example.rest.service.contact.cache.ContactCache;
import com.example.rest.service.contact.service.ContactFilterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConf.class})
public class DefaultFilterServiceTest {

    @MockBean
    private ContactCache cacheServiceMock;

    @Autowired
    private ContactFilterService cachedFilterService;

    @Test
    public void getContactsNotFound() {
        List<Contact> lst = new ArrayList<>();
        lst.add(new Contact("ATest"));
        lst.add(new Contact("Anton"));

        when(cacheServiceMock.getAllContacts()).thenReturn(lst);

        List<ContactDto> expected = new ArrayList<>();
        List<ContactDto> actual = cachedFilterService.getContacts("^A.*$");

        assertEquals(expected, actual);
    }

    @Test
    public void getContactsFoundVal() {
        List<Contact> lst = new ArrayList<>();
        lst.add(new Contact("Test"));
        lst.add(new Contact("Name"));

        when(cacheServiceMock.getAllContacts()).thenReturn(lst);
        List<ContactDto> actual = cachedFilterService.getContacts("^A.*$");

        assertEquals(2, actual.size());
    }
}