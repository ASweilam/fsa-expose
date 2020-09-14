package com.demo.fsaexpose.service;

import com.demo.fsaexpose.models.EstablishmentDetail;
import com.demo.fsaexpose.repositories.EstablishmentDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

/**
 * Tests the EstablishmentDetailService.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class EstablishmentDetailServiceTest {
    /**
     * The service that we want to test.
     */
    @Autowired
    private EstablishmentDetailService service;

    /**
     * A mock version of the EstablishmentDetailRepository for use in our tests.
     */
    @MockBean
    private EstablishmentDetailRepository repository;

    @Test
    @DisplayName("Test get one Establishment Detail - Success")
    void testGetOneEstablishmentDetailSuccess() {
        // Setup our Establishment Detail mock
        EstablishmentDetail mockEstablishmentDetail = new EstablishmentDetail(
                Long.valueOf(548860), "PI/000000417", "Mariner Bar", "Pub/bar/nightclub", Long.valueOf(7843),
                "Mariner Bar", "90 Glasgow Road", null, "Falkirk", "FK1 4HJ", "Pass",
                "fhis_pass_en-GB", "2019-06-27", "", "FHIS", false, -3.818845, 56.004435);


        doReturn(Optional.of(mockEstablishmentDetail)).when(repository).findById(548860L);

        // Execute the service call
        Optional<EstablishmentDetail> returnedEstablishmentDetail = service.get(548860L);

        // Assert the response
        Assertions.assertTrue(returnedEstablishmentDetail.isPresent(), "Establishment Detail was not found");
        Assertions.assertSame(returnedEstablishmentDetail.get(), mockEstablishmentDetail, "Establishment Details should be the same");
    }

    @Test
    @DisplayName("Test get one Establishment Detail - Not Found")
    void testGetOneEstablishmentDetailNotFound() {
        // Setup our mock
        EstablishmentDetail mockEstablishmentDetail = new EstablishmentDetail(
                Long.valueOf(548860), "PI/000000417", "Mariner Bar", "Pub/bar/nightclub", Long.valueOf(7843),
                "Mariner Bar", "90 Glasgow Road", null, "Falkirk", "FK1 4HJ", "Pass",
                "fhis_pass_en-GB", "2019-06-27", "", "FHIS", false, -3.818845, 56.004435);

        doReturn(Optional.empty()).when(repository).findById(548860L);

        // Execute the service call
        Optional<EstablishmentDetail> returnedEstablishmentDetail = service.get(548860L);

        // Assert the response
        Assertions.assertFalse(returnedEstablishmentDetail.isPresent(), "Establishment Detail shouldn't be there");
    }

    @Test
    @DisplayName("Test list() all Establishment Details")
    void testFindAll() {
        // Setup our mock
        EstablishmentDetail mockEstablishmentDetail = new EstablishmentDetail(
                Long.valueOf(548860), "PI/000000417", "Mariner Bar", "Pub/bar/nightclub", Long.valueOf(7843),
                "Mariner Bar", "90 Glasgow Road", null, "Falkirk", "FK1 4HJ", "Pass",
                "fhis_pass_en-GB", "2019-06-27", "", "FHIS", false, -3.818845, 56.004435);

        EstablishmentDetail mockEstablishmentDetail2 = mockEstablishmentDetail;
        doReturn(Arrays.asList(mockEstablishmentDetail, mockEstablishmentDetail2)).when(repository).findAll();

        // Execute the service call
        List<EstablishmentDetail> establishmentDetails = service.list();

        Assertions.assertEquals(2, establishmentDetails.size(), "should return 2 Establishment Details");
    }

    @Test
    @DisplayName("Test save a Establishment Detail")
    void testSave() {
        EstablishmentDetail mockEstablishmentDetail = new EstablishmentDetail(
                Long.valueOf(548860), "PI/000000417", "Mariner Bar", "Pub/bar/nightclub", Long.valueOf(7843),
                "Mariner Bar", "90 Glasgow Road", null, "Falkirk", "FK1 4HJ", "Pass",
                "fhis_pass_en-GB", "2019-06-27", "", "FHIS", false, -3.818845, 56.004435);

        doReturn(mockEstablishmentDetail).when(repository).saveAndFlush(any());

        EstablishmentDetail returnedEstablishmentDetail = service.create(mockEstablishmentDetail);

        Assertions.assertNotNull(returnedEstablishmentDetail, "The saved Establishment Detail should not be null");
        Assertions.assertEquals(mockEstablishmentDetail.getFhrs_id(), returnedEstablishmentDetail.getFhrs_id(), "The Establishment Details codes should be the same");
    }


}