//package com.demo.fsaexpose.mutator;
//
//
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import com.demo.fsaexpose.models.EstablishmentDetail;
//import com.demo.fsaexpose.models.LocalAuthority;
//import com.demo.fsaexpose.repositories.EstablishmentDetailRepository;
//import com.demo.fsaexpose.repositories.LocalAuthorityRepository;
//import org.springframework.stereotype.Component;
//
//public class Mutation implements GraphQLMutationResolver {
//    // ...
//    private EstablishmentDetailRepository establishmentDetailRepository;
//    private LocalAuthorityRepository localAuthorityRepository;
//
//    public Mutation(EstablishmentDetailRepository establishmentDetailRepository, LocalAuthorityRepository localAuthorityRepository) {
//        this.establishmentDetailRepository = establishmentDetailRepository;
//        this.localAuthorityRepository = localAuthorityRepository;
//    }
//
//
//    public LocalAuthority newLocalAuthority(Long code, String name, String website, String email) {
//
//        LocalAuthority localAuthority = new LocalAuthority();
//        localAuthority.setCode(code);
//        localAuthority.setName(name);
//        localAuthority.setWebsite(website);
//        localAuthority.setEmail(email);
//
//        localAuthorityRepository.saveAndFlush(localAuthority);
//
//        return localAuthority;
//    }
//
//    public EstablishmentDetail newEstablishmentDetail(Long fhrs_id, String local_authority_business_id, String business_name, String business_type,
//                                                      Long business_type_id, String address_line_1, String address_line_2, String address_line_3,
//                                                      String address_line_4, String postcode, String rating_value, String rating_key, String rating_date,
//                                                      String scores, String scheme_type, Boolean new_rating_pending, Double longitude, Double latitude,
//                                                      Long local_authority_code) {
//
//        EstablishmentDetail establishmentDetail = new EstablishmentDetail();
//        establishmentDetail.setFhrs_id(fhrs_id);
//        establishmentDetail.setLocal_authority_business_id(local_authority_business_id);
//        establishmentDetail.setBusiness_name(business_name);
//        establishmentDetail.setBusiness_type(business_type);
//        establishmentDetail.setBusiness_type_id(business_type_id);
//        establishmentDetail.setAddress_line_1(address_line_1);
//        establishmentDetail.setAddress_line_2(address_line_2);
//        establishmentDetail.setAddress_line_3(address_line_3);
//        establishmentDetail.setAddress_line_4(address_line_4);
//        establishmentDetail.setPostcode(postcode);
//        establishmentDetail.setRating_value(rating_value);
//        establishmentDetail.setRating_key(rating_key);
//        establishmentDetail.setRating_date(rating_date);
//        establishmentDetail.setScores(scores);
//        establishmentDetail.setScheme_type(scheme_type);
//        establishmentDetail.setNew_rating_pending(new_rating_pending);
//        establishmentDetail.setLongitude(longitude);
//        establishmentDetail.setLatitude(latitude);
//        establishmentDetail.setLocalAuthority(new LocalAuthority(local_authority_code));
//
//        establishmentDetailRepository.saveAndFlush(establishmentDetail);
//
//        return establishmentDetail;
//    }
//
//    public boolean deleteEstablishmentDetail(Long fhrs_id) {
//        establishmentDetailRepository.deleteById(fhrs_id);
//        return true;
//    }
//
//
//}
