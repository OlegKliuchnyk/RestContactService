package com.example.rest.service.contact.controller;

import com.example.rest.service.contact.cache.ContactCache;
import com.example.rest.service.contact.dto.ContactDto;
import com.example.rest.service.contact.service.ContactFilterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

import static java.util.Collections.singletonList;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest
public class RestContactControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ContactFilterService service;

    @Test
    public void getContactsSuccessResponse() throws Exception {
        final String nameFilter = "^A.*$";

        ContactDto contactDto = new ContactDto(1, "Test");
        List<ContactDto> contacts = singletonList(contactDto);

        given(service.getContacts(nameFilter)).willReturn(contacts);

        mvc.perform(get("/hello/contacts").param("nameFilter", nameFilter)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.contacts", hasSize(1)))
        .andExpect(jsonPath("$.contacts[0].name", is(contactDto.getName())));
    }

    @Test
    public void getContactsBadRequest() throws Exception {
        final String nameFilter = "";

        mvc.perform(get("/hello/contacts").param("nameFilter", nameFilter)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getContactsNoContent() throws Exception {
        final String nameFilter = "^A.*$";

        given(service.getContacts(nameFilter)).willReturn(new ArrayList<>());

        mvc.perform(get("/hello/contacts").param("nameFilter", nameFilter)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}