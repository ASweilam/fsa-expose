package com.demo.fsaexpose.service;

import com.demo.fsaexpose.models.EstablishmentDetail;

import java.util.List;
import java.util.Optional;

public interface EstablishmentDetailService {

    //Get List
    List<EstablishmentDetail> list();

    //Get one object
    Optional<EstablishmentDetail> get(Long fhrs_id);


    //CREATE
    EstablishmentDetail create(final EstablishmentDetail establishmentDetail);

    //DELETE
    void delete(Long fhrs_id);

    //UPDATE
    EstablishmentDetail update(Long fhrs_id, EstablishmentDetail establishmentDetail);


}
