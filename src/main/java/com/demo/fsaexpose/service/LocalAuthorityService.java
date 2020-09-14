package com.demo.fsaexpose.service;

import com.demo.fsaexpose.models.LocalAuthority;

import java.util.List;
import java.util.Optional;

public interface LocalAuthorityService {

    /**
     * Returns all local authorities in the database.
     *
     * @return          All local authorities in the database.
     */
    List<LocalAuthority> list();


    /**
     * Returns the Local Authority with the specified code.
     *
     * @param code        Code of the Local Authority to retrieve.
     * @return            The requested Local Authority if found.
     */
    Optional<LocalAuthority> get(Long code);

    //CREATE
    LocalAuthority create(LocalAuthority localAuthority);

    //delete
    void delete(Long code);

    //UPDATE
    LocalAuthority update(Long code, LocalAuthority localAuthority);


}
