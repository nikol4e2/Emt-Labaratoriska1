package com.example.labaratoriska1.service;

import com.example.labaratoriska1.model.Author;
import com.example.labaratoriska1.model.Book;
import com.example.labaratoriska1.model.dto.BookDto;
import com.example.labaratoriska1.model.enumerations.BookCategory;
import jdk.jfr.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> edit(String name, BookCategory category, Author author, int availableCopies);
    Optional<Book> edit(Long id,BookDto bookDto);

    Optional<Book> save(BookDto bookDto);



    Book create(String name, BookCategory category, Author author, int availableCopies);



    void deleteByName(String name);

    void deleteById(Long id);

    void markAsTaken(Long id);




}
