package com.example.labaratoriska1.service.impl;

import com.example.labaratoriska1.model.Author;
import com.example.labaratoriska1.model.Book;
import com.example.labaratoriska1.model.enumerations.BookCategory;
import com.example.labaratoriska1.model.exceptions.BookNotFoundException;
import com.example.labaratoriska1.model.exceptions.BookWithIdNotFoundException;
import com.example.labaratoriska1.repository.AuthorRepository;
import com.example.labaratoriska1.repository.BookRepository;
import com.example.labaratoriska1.service.BookService;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> edit(String name, BookCategory category, Author author, int availableCopies) {
        Book book=this.bookRepository.findByName(name)
                .orElseThrow(()-> new BookNotFoundException(name));

        book.setBookCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return Optional.of(this.bookRepository.save(book));

    }

    @Override
    public Book create(String name, BookCategory category, Author author, int availableCopies) {
        return this.bookRepository.save(new Book(name,category,author,availableCopies));


    }

    @Override
    public void deleteByName(String name) {
        this.bookRepository.deleteByName(name);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void markAsTaken(Long id) {
        Book book=this.bookRepository.findById(id)
                .orElseThrow(()-> new BookWithIdNotFoundException(id));
        book.setAvailableCopies(book.getAvailableCopies()-1);
        this.bookRepository.save(book);
    }
}
