package com.example.labaratoriska1.service;

import com.example.labaratoriska1.model.Author;
import com.example.labaratoriska1.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> findByName(String name);

    Author create(String name, String surname, Country country);

    Optional<Author> edit(Long id,String name, String surname, Country country);

    void deleteById(Long id);

    void deleteByName(String name);
}
