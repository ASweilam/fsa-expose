package com.demo.fsaexpose.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.fsaexpose.models.EstablishmentDetail;
import com.demo.fsaexpose.models.LocalAuthority;
import com.demo.fsaexpose.repositories.EstablishmentDetailRepository;
import com.demo.fsaexpose.repositories.LocalAuthorityRepository;
import org.springframework.stereotype.Component;

public class Query implements GraphQLQueryResolver {
    // ...
    private EstablishmentDetailRepository establishmentDetailRepository;
    private LocalAuthorityRepository localAuthorityRepository;

    public Query(EstablishmentDetailRepository establishmentDetailRepository, LocalAuthorityRepository localAuthorityRepository) {
        this.establishmentDetailRepository = establishmentDetailRepository;
        this.localAuthorityRepository = localAuthorityRepository;
    }

    public Iterable<EstablishmentDetail> findAllEstablishmentDetails() {
        return establishmentDetailRepository.findAll();
    }

    public EstablishmentDetail findEstablishmentDetail(Long fhrs_id) {
        return establishmentDetailRepository.getOne(fhrs_id);
    }


    public Iterable<LocalAuthority> findAllLocalAuthorities() {
        return localAuthorityRepository.findAll();
    }

    public long countEstablishmentDetails() {
        return establishmentDetailRepository.count();
    }

    public long countLocalAuthorities() {
        return localAuthorityRepository.count();
    }


}
