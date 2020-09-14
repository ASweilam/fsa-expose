package com.demo.fsaexpose.controllers;

import com.demo.fsaexpose.models.EstablishmentDetail;
import com.demo.fsaexpose.service.EstablishmentDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/details")
public class EstablishmentDetailController {
    private static final Logger logger = LogManager.getLogger(EstablishmentDetailController.class);

    private EstablishmentDetailService service;

    public EstablishmentDetailController(EstablishmentDetailService service) {
        this.service = service;
    }

    @GetMapping
    public List<EstablishmentDetail> list() {
        return service.list();
    }


    @GetMapping("/{fhrs_id}")
//    @RequestMapping("{fhrs_id}")
    public ResponseEntity<?> get(@PathVariable Long fhrs_id) {
        return service.get(fhrs_id)
                .map(establishmentDetail -> {
                    return ResponseEntity
                            .ok()
                            .body(establishmentDetail);
                })
                .orElse(ResponseEntity.notFound().build());
//        return establishmentDetailRepository.findById(fhrs_id);
    }


    //CREATE
    @PostMapping
    public ResponseEntity<EstablishmentDetail> create(@RequestBody final EstablishmentDetail establishmentDetail) {
        logger.info("Creating new Establishment Detail");

        EstablishmentDetail newEstablishmentDetail = service.create(establishmentDetail);
        logger.info("Saved new Establishment Detail: {}", newEstablishmentDetail);

        try {
            // Build a created response
            return ResponseEntity
                    .created(new URI("/" + newEstablishmentDetail.getFhrs_id()))
                    .body(newEstablishmentDetail);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DELETE
    @DeleteMapping("/{fhrs_id}")
    public ResponseEntity<?> delete(@PathVariable Long fhrs_id) {
        logger.info("Initiate deleting establishment detail with fhrs_id: {}", fhrs_id);

        Optional<EstablishmentDetail> existingEstablishmentDetail = service.get(fhrs_id);

        logger.info("Found an Establishment Detail with fhrs_id: {}. \n {}", fhrs_id, existingEstablishmentDetail);

        // Delete the Establishment Detail if it exists in the database
        return existingEstablishmentDetail.map(es -> {
            service.delete(es.getFhrs_id());
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    //UPDATE
    @PutMapping("/{fhrs_id}")
    public ResponseEntity<?> update(@PathVariable Long fhrs_id,
                                    @RequestBody EstablishmentDetail establishmentDetail) {
        logger.info("Updating Establishment Detail fhrs_id: {}", fhrs_id);

        //Get the existing establishment detail
        Optional<EstablishmentDetail> existingEstablishmentDetail = service.get(fhrs_id);

        return existingEstablishmentDetail.map(es -> {

            // Update the Establishment Detail
            BeanUtils.copyProperties(establishmentDetail, es, "fhrs_id");

            try {
                // Update the Establishment Detail and return an ok response
                EstablishmentDetail updatedEstablishmentDetail = service.update(fhrs_id, es);

                if (updatedEstablishmentDetail != null)  {
                    return ResponseEntity.ok()
                            .location(new URI("/" + es.getFhrs_id()))
                            .body(es);
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
