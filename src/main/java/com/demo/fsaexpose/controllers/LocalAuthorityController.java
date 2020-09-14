package com.demo.fsaexpose.controllers;

import com.demo.fsaexpose.models.LocalAuthority;
import com.demo.fsaexpose.service.LocalAuthorityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authority")
public class LocalAuthorityController {
    private static final Logger logger = LogManager.getLogger(LocalAuthorityController.class);

    private final LocalAuthorityService service;

    public LocalAuthorityController(LocalAuthorityService service) {
        this.service = service;
    }

    @GetMapping
    private List<LocalAuthority> list() {
        return service.list();
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> get(@PathVariable Long code) {
        return service.get(code)
                .map(la -> {
                    return ResponseEntity
                            .ok()
                            .body(la);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //CREATE
    @PostMapping
    public ResponseEntity<LocalAuthority> create(@RequestBody final LocalAuthority localAuthority) {
        logger.info("Creating a new Local Authority");

        LocalAuthority newAuthority = service.create(localAuthority);

        logger.info("Saved new Local Authority: {}", newAuthority);

        try {
            // Build a created response
            return ResponseEntity
                    .created(new URI("/" + newAuthority.getCode()))
                    .body(newAuthority);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //DELETE
    @DeleteMapping("/{code}")
    public ResponseEntity<?> delete(@PathVariable Long code) {

        logger.info("Initiate deleting Local Authority with code: {}", code);

        Optional<LocalAuthority> existingLocalAuthority = service.get(code);

        logger.info("Found a Local Authority with code {} : \n \t {}", code, existingLocalAuthority);

        // Delete the Local Authority if it exists in the database
        return existingLocalAuthority.map(ela -> {
            service.delete(ela.getCode());
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());

    }

    //UPDATE
    @PutMapping("/{code}")
    public ResponseEntity<?> update(@PathVariable Long code,
                                    @RequestBody LocalAuthority localAuthority) {

        logger.info("Updating a Local Authority with code: {}", code);

        //Get the existing local Authority in the database
        Optional<LocalAuthority> existingLocalAuthority = service.get(code);

        return existingLocalAuthority.map(ela -> {

            // Update the Local Authority
            BeanUtils.copyProperties(localAuthority, ela, "code");

            try {
                // Update the Local Authority and return an ok response
                LocalAuthority updatedLocalAuthority = service.update(code, ela);

                if (updatedLocalAuthority != null) {
                    return ResponseEntity.ok()
                            .location(new URI("/" + ela.getCode()))
                            .body(ela);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (URISyntaxException e) {
                // An error occurred trying to create the location URI, return an error
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }).orElse(ResponseEntity.notFound().build());

    }


}
