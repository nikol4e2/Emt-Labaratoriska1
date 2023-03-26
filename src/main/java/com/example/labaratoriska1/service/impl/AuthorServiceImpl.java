package com.example.labaratoriska1.service.impl;

import com.example.labaratoriska1.model.Author;
import com.example.labaratoriska1.model.Country;
import com.example.labaratoriska1.model.exceptions.AuthorNotFound;
import com.example.labaratoriska1.repository.AuthorRepository;
import com.example.labaratoriska1.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return this.authorRepository.findByName(name);
    }

    @Override
    public Author create(String name, String surname, Country country) {
        return this.authorRepository.save(new Author(name,surname,country));
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Country country) {
        Author author=this.authorRepository.findById(id)
                .orElseThrow(()->new AuthorNotFound(id));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        this.authorRepository.deleteByName(name);

    }
}
