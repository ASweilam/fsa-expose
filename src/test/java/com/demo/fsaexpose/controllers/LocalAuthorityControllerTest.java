package com.demo.fsaexpose.controllers;

import com.demo.fsaexpose.models.LocalAuthority;
import com.demo.fsaexpose.service.LocalAuthorityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LocalAuthorityControllerTest {


    @MockBean
    private LocalAuthorityService service;

    @Autowired
    private MockMvc mockMvc;

    /**********************************************************
     * GET Tests
     * ********************************************************/

    @Test
    @DisplayName("Get /api/v1/authority/777 - Found")
    void testGetLocalAuthorityByIdFound() throws Exception {
        // Setup our mocked service

        LocalAuthority mockLocalAuthority = new LocalAuthority(Long.valueOf(777), "Edinburgh", "http://www.ed.gov.uk/", "ed@ed.gov.uk");

        doReturn(Optional.of(mockLocalAuthority)).when(service).get(777L);

        mockMvc.perform(get("/api/v1/authority/{code}", 777))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))


                // Validate the returned fields
                .andExpect(jsonPath("$.code", is(777)))
                .andExpect(jsonPath("$.name", is("Edinburgh")))
                .andExpect(jsonPath("$.website", is("http://www.ed.gov.uk/")))
                .andExpect(jsonPath("$.email", is("ed@ed.gov.uk")));
    }


    @Test
    @DisplayName("Get /api/v1/authority/777 - Not Found")
    void testGetLocalAuthorityByIdNotFound() throws Exception {
        // Setup our mocked service
        doReturn(Optional.empty()).when(service).get(777L);

        // Execute the GET request
        mockMvc.perform(get("/api/v1/authority/{code}", 777))

                // Validate that we get a 404 Not Found response
                .andExpect(status().isNotFound());
    }

    /**********************************************************
     * POST Tests
     * ********************************************************/

    @Test
    @DisplayName("POST /api/v1/authority - Success")
    void testCreateLocalAuthority() throws Exception {
        // Setup mocked service
        LocalAuthority mockLocalAuthority = new LocalAuthority(Long.valueOf(777), "Edinburgh", "http://www.ed.gov.uk/", "ed@ed.gov.uk");

        LocalAuthority postLocalAuthority = mockLocalAuthority;


        doReturn(mockLocalAuthority).when(service).create(any());

        mockMvc.perform(post("/api/v1/authority")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postLocalAuthority)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                // Validate the header
                .andExpect(header().string(HttpHeaders.LOCATION, "/777"))

                // Validate the returned fields
                .andExpect(jsonPath("$.code", is(777)))
                .andExpect(jsonPath("$.name", is("Edinburgh")))
                .andExpect(jsonPath("$.website", is("http://www.ed.gov.uk/")))
                .andExpect(jsonPath("$.email", is("ed@ed.gov.uk")));
    }

    /**********************************************************
     * PUT Tests
     * ********************************************************/

    @Test
    @DisplayName("PUT /api/v1/authority/777 - Success")
    void testLocalAuthorityPutSuccess() throws Exception {
        // Setup mocked service
        LocalAuthority mockLocalAuthority = new LocalAuthority(Long.valueOf(777), "Edinburgh", "http://www.ed.gov.uk/", "ed@ed.gov.uk");

        LocalAuthority putLocalAuthority = mockLocalAuthority;

        doReturn(Optional.of(mockLocalAuthority)).when(service).get(777L);
        doReturn(mockLocalAuthority).when(service).update(putLocalAuthority.getCode(), putLocalAuthority);

        mockMvc.perform(put("/api/v1/authority/{code}", putLocalAuthority.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putLocalAuthority)))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                // Validate the headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/777"))

                // Validate the returned fields
                .andExpect(jsonPath("$.code", is(777)))
                .andExpect(jsonPath("$.name", is("Edinburgh")))
                .andExpect(jsonPath("$.website", is("http://www.ed.gov.uk/")))
                .andExpect(jsonPath("$.email", is("ed@ed.gov.uk")));
    }


    @Test
    @DisplayName("PUT /api/v1/authority/777 - Not Found")
    void testLocalAuthorityPutNotFound() throws Exception {
        // Setup mocked service
        LocalAuthority mockLocalAuthority = new LocalAuthority(Long.valueOf(777), "Edinburgh", "http://www.ed.gov.uk/", "ed@ed.gov.uk");

        doReturn(Optional.empty()).when(service).get(777L);

        mockMvc.perform(put("/api/v1/authority/{code}", 777)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockLocalAuthority)))

                // Validate the response code and content type
                .andExpect(status().isNotFound());
    }


    /**********************************************************
     * DELETE Tests
     * ********************************************************/

    @Test
    @DisplayName("DELETE /api/v1/authority/777 - Success")
    void testLocalAuthorityDeleteSuccess() throws Exception {
        // Setup mocked Local Authority
        LocalAuthority mockLocalAuthority = new LocalAuthority(Long.valueOf(777), "Edinburgh", "http://www.ed.gov.uk/", "ed@ed.gov.uk");

        // Setup the mocked service
        doReturn(Optional.of(mockLocalAuthority)).when(service).get(777L);
        doNothing().when(service).delete(548860L);

        // Execute our DELETE request
        mockMvc.perform(delete("/api/v1/authority/{code}", 777))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("DELETE /api/v1/authority/777 - Not Found")
    void testLocalAuthorityDeleteNotFound() throws Exception {
        // Setup the mocked service
        doReturn(Optional.empty()).when(service).get(777L);

        // Execute our DELETE request
        mockMvc.perform(delete("/api/v1/authority/{code}", 777L))
                .andExpect(status().isNotFound());
    }


    /***
     * Helper method for test that returns the String representation of an Object.
     * @param       obj  an Object to get the String representation for
     * @return A String representation of the Object parameter.
     */
    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}