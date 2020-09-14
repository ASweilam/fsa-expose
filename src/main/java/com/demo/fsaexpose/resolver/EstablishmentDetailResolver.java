//package com.demo.fsaexpose.resolver;
//
//import com.coxautodev.graphql.tools.GraphQLResolver;
//import com.demo.fsaexpose.models.EstablishmentDetail;
//import com.demo.fsaexpose.models.LocalAuthority;
//import com.demo.fsaexpose.repositories.EstablishmentDetailRepository;
//import com.demo.fsaexpose.repositories.LocalAuthorityRepository;
//import org.springframework.stereotype.Component;
//
//
//public class EstablishmentDetailResolver implements GraphQLResolver<EstablishmentDetail> {
//
//    private LocalAuthorityRepository localAuthorityRepository;
//    private EstablishmentDetailRepository establishmentDetailRepository;
//
//
//    public EstablishmentDetailResolver(LocalAuthorityRepository localAuthorityRepository) {
//        this.localAuthorityRepository = localAuthorityRepository;
//    }
//
//    public LocalAuthority getLocalAuthority(EstablishmentDetail establishmentDetail) {
//        return localAuthorityRepository.findByIdAndFetchLocalAuthority(establishmentDetail.getLocalAuthority().getCode());
//
//    }
//
//
//}
