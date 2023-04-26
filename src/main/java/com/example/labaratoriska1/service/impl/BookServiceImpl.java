package com.example.labaratoriska1.service.impl;

import com.example.labaratoriska1.model.Author;
import com.example.labaratoriska1.model.Book;
import com.example.labaratoriska1.model.dto.BookDto;
import com.example.labaratoriska1.model.enumerations.BookCategory;
import com.example.labaratoriska1.model.exceptions.AuthorNotFound;
import com.example.labaratoriska1.model.exceptions.BookNotFoundException;
import com.example.labaratoriska1.model.exceptions.BookWithIdNotFoundException;
import com.example.labaratoriska1.repository.AuthorRepository;
import com.example.labaratoriska1.repository.BookRepository;
import com.example.labaratoriska1.service.BookService;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
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
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
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
        if(book.getAvailableCopies()>0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }
        this.bookRepository.save(book);
    }



    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author=this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(()->new AuthorNotFound(bookDto.getAuthor()));

//        this.bookRepository.deleteByName(bookDto.getName());
        Book book=new Book(bookDto.getName(), bookDto.getBookCategory(), author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return  Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book=this.bookRepository.findById(id)
                .orElseThrow(()->new BookWithIdNotFoundException(id));

        book.setName(bookDto.getName());
        book.setBookCategory(bookDto.getBookCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());

        Author author=this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(()->new AuthorNotFound(bookDto.getAuthor()));

        book.setAuthor(author);
        return Optional.of(this.bookRepository.save(book));
    }
}
