package com.demo.fsaexpose.service;

import com.demo.fsaexpose.models.LocalAuthority;
import com.demo.fsaexpose.repositories.LocalAuthorityRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalAuthorityServiceImpl implements LocalAuthorityService {
    private static final Logger logger = LogManager.getLogger(LocalAuthorityServiceImpl.class);

    private final LocalAuthorityRepository localAuthorityRepository;

    public LocalAuthorityServiceImpl(LocalAuthorityRepository localAuthorityRepository) {
        this.localAuthorityRepository = localAuthorityRepository;
    }

    @Override
    public List<LocalAuthority> list() {
        logger.info("Find all Local Authorities");
        return localAuthorityRepository.findAll();
    }

    @Override
    public Optional<LocalAuthority> get(Long code) {
        logger.info("Find a Local Authority with code: {}", code);
        return localAuthorityRepository.findById(code);
    }

    @Override
    public LocalAuthority create(LocalAuthority localAuthority) {
        logger.info("Save a new Local Authority to the database: {}", localAuthority);
        return localAuthorityRepository.saveAndFlush(localAuthority);
    }

    @Override
    public void delete(Long code) {
        logger.info("Delete the Local Authority from the database with code: {}", code);
        localAuthorityRepository.deleteById(code);
    }

    @Override
    public LocalAuthority update(Long code, LocalAuthority localAuthority) {
        logger.info("Update Local Authority in the database: {}", localAuthority);

        LocalAuthority existingLocalAuthority = localAuthorityRepository.getOne(code);

        BeanUtils.copyProperties(localAuthority, existingLocalAuthority, "code");

        return localAuthorityRepository.saveAndFlush(existingLocalAuthority);
    }
}
