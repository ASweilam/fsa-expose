package com.demo.fsaexpose.repositories;

import com.demo.fsaexpose.models.LocalAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//Setup repository for data layer. This allows for Find, Update, Save, Delete...etc

public interface LocalAuthorityRepository extends JpaRepository<LocalAuthority, Long> {

    ////    @Query("SELECT l FROM local_authority l JOIN FETCH l.establishmentDetails WHERE l.code = (:code)")
    @Query("SELECT l FROM local_authority l WHERE l.code = (:code)")
    public LocalAuthority findByIdAndFetchLocalAuthority(@Param("code") Long code);

}
