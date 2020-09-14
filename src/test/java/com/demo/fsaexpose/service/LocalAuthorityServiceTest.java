package com.demo.fsaexpose.service;

import com.demo.fsaexpose.models.LocalAuthority;
import com.demo.fsaexpose.repositories.LocalAuthorityRepository;
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
 * Tests the LocalAuthorityService.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class LocalAuthorityServiceTest {

    /**
     * The service that we want to test.
     */
    @Autowired
    private LocalAuthorityService service;

    /**
     * A mock version of the LocalAuthorityRepository for use in our tests.
     */
    @MockBean
    private LocalAuthorityRepository repository;

    @Test
    @DisplayName("Test get one local authority - Success")
    void testGetOneLocalAuthoritySuccess() {
        // Setup our Local Authority mock
        LocalAuthority mockLocalAuthority = new LocalAuthority(Long.valueOf(777), "Edinburgh", "http://www.ed.gov.uk/", "ed@ed.gov.uk");
        doReturn(Optional.of(mockLocalAuthority)).when(repository).findById(777L);

        // Execute the service call
        Optional<LocalAuthority> returnedLocalAuthority = service.get(777L);

        // Assert the response
        Assertions.assertTrue(returnedLocalAuthority.isPresent(), "Local Authority was not found");
        Assertions.assertSame(returnedLocalAuthority.get(), mockLocalAuthority, "Local Authorities should be the same");
    }

    @Test
    @DisplayName("Test get one local authority - Not Found")
    void testGetOneLocalAuthorityNotFound() {
        // Setup our mock
        LocalAuthority mockLocalAuthority = new LocalAuthority(Long.valueOf(777), "Edinburgh", "http://www.ed.gov.uk/", "ed@ed.gov.uk");
        doReturn(Optional.empty()).when(repository).findById(777L);

        // Execute the service call
        Optional<LocalAuthority> returnedLocalAuthority = service.get(777L);

        // Assert the response
        Assertions.assertFalse(returnedLocalAuthority.isPresent(), "Local Authority shouldn't be there");
    }

    @Test
    @DisplayName("Test list() all Local Authorities")
    void testFindAll() {
        // Setup our mock
        LocalAuthority mockLocalAuthority = new LocalAuthority(Long.valueOf(777), "Edinburgh", "http://www.ed.gov.uk/", "ed@ed.gov.uk");
        LocalAuthority mockLocalAuthority2 = mockLocalAuthority;
        doReturn(Arrays.asList(mockLocalAuthority, mockLocalAuthority2)).when(repository).findAll();

        // Execute the service call
        List<LocalAuthority> localAuthorities = service.list();

        Assertions.assertEquals(2, localAuthorities.size(), "should return 2 Local Authorities");
    }

    @Test
    @DisplayName("Test save a Local Authority")
    void testSave() {
        LocalAuthority mockLocalAuthority = new LocalAuthority(Long.valueOf(777), "Edinburgh", "http://www.ed.gov.uk/", "ed@ed.gov.uk");
        doReturn(mockLocalAuthority).when(repository).saveAndFlush(any());

        LocalAuthority returnedLocalAuthority = service.create(mockLocalAuthority);

        Assertions.assertNotNull(returnedLocalAuthority, "The saved Local Authority should not be null");
        Assertions.assertEquals(mockLocalAuthority.getCode(), returnedLocalAuthority.getCode(),"The Local Authorities codes should be the same");
    }

}