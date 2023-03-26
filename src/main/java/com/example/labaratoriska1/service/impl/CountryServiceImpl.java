package com.example.labaratoriska1.service.impl;

import com.example.labaratoriska1.model.Country;
import com.example.labaratoriska1.repository.CountryRepository;
import com.example.labaratoriska1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl  implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
      return   this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findByName(String name) {
        return this.countryRepository.findByName(name);
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Country create(String name, String continent) {
        return this.countryRepository.save(new Country(name,continent));
    }

    @Override
    public void deleteByName(String name) {
        this.countryRepository.deleteByName(name);
    }
}
