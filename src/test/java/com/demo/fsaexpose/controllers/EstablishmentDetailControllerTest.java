package com.demo.fsaexpose.controllers;

import com.demo.fsaexpose.models.EstablishmentDetail;
import com.demo.fsaexpose.models.LocalAuthority;
import com.demo.fsaexpose.service.EstablishmentDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNull;
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
@SpringBootTest
@AutoConfigureMockMvc
class EstablishmentDetailControllerTest {

    @MockBean
    private EstablishmentDetailService service;

    @Autowired
    private MockMvc mockMvc;


    /**********************************************************
     * GET Tests
     * ********************************************************/

    @Test
    @DisplayName("Get /api/v1/details/548860 - Found")
    void testGetEstablishmentDetailByIdFound() throws Exception {
        // Setup our mocked service

        EstablishmentDetail mockEstablishmentDetail = new EstablishmentDetail(
                Long.valueOf(548860), "PI/000000417", "Mariner Bar", "Pub/bar/nightclub", Long.valueOf(7843),
                "Mariner Bar", "90 Glasgow Road", null, "Falkirk", "FK1 4HJ", "Pass",
                "fhis_pass_en-GB", "2019-06-27", "", "FHIS", false, -3.818845, 56.004435);

        doReturn(Optional.of(mockEstablishmentDetail)).when(service).get(548860L);

        mockMvc.perform(get("/api/v1/details/{fhrs_id}", 548860))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))


                // Validate the returned fields
                .andExpect(jsonPath("$.fhrs_id", is(548860)))
                .andExpect(jsonPath("$.postcode", is("FK1 4HJ")))
                .andExpect(jsonPath("$.address_line_3", is(IsNull.nullValue())))
                .andExpect(jsonPath("$.new_rating_pending", is(false)))
                .andExpect(jsonPath("$.longitude", is(-3.818845)))
                .andExpect(jsonPath("$.latitude", is(56.004435)));
    }


    @Test
    @DisplayName("Get /api/v1/details/548860 - Not Found")
    void testGetProductByIdNotFound() throws Exception {
        // Setup our mocked service
        doReturn(Optional.empty()).when(service).get(548860L);

        // Execute the GET request
        mockMvc.perform(get("/api/v1/details/{fhrs_id}", 548860))

                // Validate that we get a 404 Not Found response
                .andExpect(status().isNotFound());
    }

    /**********************************************************
     * POST Tests
     * ********************************************************/

    @Test
    @DisplayName("POST /api/v1/details - Success")
    void testCreateEstablishmentDetail() throws Exception {
        // Setup mocked service
        EstablishmentDetail mockEstablishmentDetail = new EstablishmentDetail(
                Long.valueOf(548860), "PI/000000417", "Mariner Bar", "Pub/bar/nightclub", Long.valueOf(7843),
                "Mariner Bar", "90 Glasgow Road", null, "Falkirk", "FK1 4HJ", "Pass",
                "fhis_pass_en-GB", "2019-06-27", "", "FHIS", false, -3.818845, 56.004435);

        EstablishmentDetail postEstablishmentDetail = mockEstablishmentDetail;


        doReturn(mockEstablishmentDetail).when(service).create(any());

        mockMvc.perform(post("/api/v1/details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postEstablishmentDetail)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                // Validate the header
                .andExpect(header().string(HttpHeaders.LOCATION, "/548860"))

                // Validate the returned fields
                .andExpect(jsonPath("$.fhrs_id", is(548860)))
                .andExpect(jsonPath("$.postcode", is("FK1 4HJ")))
                .andExpect(jsonPath("$.address_line_3", is(IsNull.nullValue())))
                .andExpect(jsonPath("$.new_rating_pending", is(false)))
                .andExpect(jsonPath("$.longitude", is(-3.818845)))
                .andExpect(jsonPath("$.latitude", is(56.004435)));
    }

    /**********************************************************
     * PUT Tests
     * ********************************************************/

    @Test
    @DisplayName("PUT /api/v1/details/548860 - Success")
    void testEstablishmentDetailPutSuccess() throws Exception {
        // Setup mocked service
        EstablishmentDetail mockEstablishmentDetail = new EstablishmentDetail(
                Long.valueOf(548860), "PI/000000417", "Mariner Bar", "Pub/bar/nightclub", Long.valueOf(7843),
                "Mariner Bar", "90 Glasgow Road", null, "Falkirk", "FK1 4HJ", "Pass",
                "fhis_pass_en-GB", "2019-06-27", "", "FHIS", false, -3.818845, 56.004435);

        EstablishmentDetail putEstablishmentDetail = mockEstablishmentDetail;

        doReturn(Optional.of(mockEstablishmentDetail)).when(service).get(548860L);
        doReturn(mockEstablishmentDetail).when(service).update(putEstablishmentDetail.getFhrs_id(), putEstablishmentDetail);

        mockMvc.perform(put("/api/v1/details/{fhrs_id}", putEstablishmentDetail.getFhrs_id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putEstablishmentDetail)))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                // Validate the headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/548860"))

                // Validate the returned fields
                .andExpect(jsonPath("$.fhrs_id", is(548860)))
                .andExpect(jsonPath("$.postcode", is("FK1 4HJ")))
                .andExpect(jsonPath("$.address_line_3", is(IsNull.nullValue())))
                .andExpect(jsonPath("$.new_rating_pending", is(false)))
                .andExpect(jsonPath("$.longitude", is(-3.818845)))
                .andExpect(jsonPath("$.latitude", is(56.004435)));
    }


    @Test
    @DisplayName("PUT /api/v1/details/548860 - Not Found")
    void testEstablishmentDetailPutNotFound() throws Exception {
        // Setup mocked service
        EstablishmentDetail putEstablishmentDetail = new EstablishmentDetail(
                Long.valueOf(548860), "PI/000000417", "Mariner Bar", "Pub/bar/nightclub", Long.valueOf(7843),
                "Mariner Bar", "90 Glasgow Road", null, "Falkirk", "FK1 4HJ", "Pass",
                "fhis_pass_en-GB", "2019-06-27", "", "FHIS", false, -3.818845, 56.004435);

        doReturn(Optional.empty()).when(service).get(548860L);

        mockMvc.perform(put("/api/v1/details/{fhrs_id}", 548860)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putEstablishmentDetail)))

                // Validate the response code and content type
                .andExpect(status().isNotFound());
    }


    /**********************************************************
     * DELETE Tests
     * ********************************************************/

    @Test
    @DisplayName("DELETE /api/v1/details/548860 - Success")
    void testEstablishmentDetailDeleteSuccess() throws Exception {
        // Setup mocked Establishment Detail
        EstablishmentDetail mockEstablishmentDetail = new EstablishmentDetail(
                Long.valueOf(548860), "PI/000000417", "Mariner Bar", "Pub/bar/nightclub", Long.valueOf(7843),
                "Mariner Bar", "90 Glasgow Road", null, "Falkirk", "FK1 4HJ", "Pass",
                "fhis_pass_en-GB", "2019-06-27", "", "FHIS", false, -3.818845, 56.004435);

        // Setup the mocked service
        doReturn(Optional.of(mockEstablishmentDetail)).when(service).get(548860L);
        doNothing().when(service).delete(548860L);

        // Execute our DELETE request
        mockMvc.perform(delete("/api/v1/details/{fhrs_id}", 548860))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("DELETE /api/v1/details/548860 - Not Found")
    void testEstablishmentDetailDeleteNotFound() throws Exception {
        // Setup the mocked service
        doReturn(Optional.empty()).when(service).get(548860L);

        // Execute our DELETE request
        mockMvc.perform(delete("/api/v1/details/{fhrs_id}", 548860L))
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