package com.demo.fsaexpose.service;

import com.demo.fsaexpose.models.EstablishmentDetail;
import com.demo.fsaexpose.repositories.EstablishmentDetailRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentDetailServiceImpl implements EstablishmentDetailService {

    private static final Logger logger = LogManager.getLogger(EstablishmentDetailServiceImpl.class);

    private EstablishmentDetailRepository repository;

    public EstablishmentDetailServiceImpl(EstablishmentDetailRepository repository) {
        this.repository = repository;
    }



    @Override
    public List<EstablishmentDetail> list() {
        return repository.findAll();
    }

    @Override
    public Optional<EstablishmentDetail> get(Long fhrs_id) {
        return repository.findById(fhrs_id);
    }

    @Override
    public EstablishmentDetail create(final EstablishmentDetail establishmentDetail) {
        return repository.saveAndFlush(establishmentDetail);
    }

    @Override
    public void delete(Long fhrs_id) {
        logger.info("Delete Establishment Detail with fhrs_id: {}", fhrs_id);
         repository.deleteById(fhrs_id);
    }

    @Override
    public EstablishmentDetail update(Long fhrs_id, EstablishmentDetail establishmentDetail) {
        EstablishmentDetail existingEstablishmentDetail = repository.getOne(fhrs_id);
        BeanUtils.copyProperties(establishmentDetail, existingEstablishmentDetail, "fhrs_id");

        return repository.saveAndFlush(existingEstablishmentDetail);
    }
}
