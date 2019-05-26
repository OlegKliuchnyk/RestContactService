package com.example.rest.service.contact.service.impl;

import com.example.rest.service.contact.dto.ContactDto;
import com.example.rest.service.contact.entity.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class DefaultCachedFilterServiceTest {
    @MockBean
    private DefaultContactCacheService cacheService;

    private DefaultCachedFilterService cachedFilterService;

    @Before
    public void init() {
        cachedFilterService = new DefaultCachedFilterService(cacheService);
    }

    @Test
    public void getContactsNotFound() {
        List<Contact> lst = new ArrayList<>();
        lst.add(new Contact("ATest"));
        lst.add(new Contact("Anton"));

        when(cacheService.getAllContacts()).thenReturn(lst);

        List<ContactDto> expected = new ArrayList<>();
        List<ContactDto> actual = cachedFilterService.getContacts("^A.*$");

        assertEquals(expected, actual);
    }

    @Test
    public void getContactsFoundVal() {
        List<Contact> lst = new ArrayList<>();
        lst.add(new Contact("Test"));
        lst.add(new Contact("Name"));

        when(cacheService.getAllContacts()).thenReturn(lst);


        List<ContactDto> actual = cachedFilterService.getContacts("^A.*$");

        assertEquals(2, actual.size());
    }
}