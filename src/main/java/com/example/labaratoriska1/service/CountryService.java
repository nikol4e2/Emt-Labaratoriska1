package com.example.labaratoriska1.service;

import com.example.labaratoriska1.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findByName(String name);
    Optional<Country> findById(Long id);

    Country create(String name, String continent);
    void deleteByName(String name);

}
