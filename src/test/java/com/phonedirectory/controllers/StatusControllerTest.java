package com.phonedirectory.controllers;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StatusControllerTest {
    private StatusController statusController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        statusController = new StatusController();
        mockMvc = MockMvcBuilders.standaloneSetup(statusController).build();
    }

    @Test
    void testGetStatusURI() throws Exception {
        mockMvc.perform(get("/status")).andExpect(status().isOk());
    }

    @Test
    void testGetStatus() {
        // given
        String response = "OK";

        // when
        ResponseEntity<String> responseEntity = statusController.getStatus();

        // then
        assertThat(responseEntity.getBody(), is(response));
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }
}