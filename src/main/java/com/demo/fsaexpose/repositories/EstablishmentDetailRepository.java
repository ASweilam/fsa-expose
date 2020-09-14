package com.demo.fsaexpose.repositories;

import com.demo.fsaexpose.models.EstablishmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

//Setup repository for data layer. This allows for Find, Update, Save, Delete...etc

public interface EstablishmentDetailRepository extends JpaRepository<EstablishmentDetail, Long> {

//    @Query("SELECT e FROM establishment_detail e WHERE e.fhrs_id = (:fhrs_id)")
////    @Query("SELECT l FROM local_authority l JOIN FETCH l.establishmentDetails WHERE l.code = (:code)")
//    public EstablishmentDetail findByIdAndFetchEstablishmentDetail(@Param("fhrs_id") Long fhrs_id);
}
