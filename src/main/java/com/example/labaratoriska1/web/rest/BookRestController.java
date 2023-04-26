package com.example.labaratoriska1.web.rest;


import com.example.labaratoriska1.model.Author;
import com.example.labaratoriska1.model.Book;
import com.example.labaratoriska1.model.dto.BookDto;
import com.example.labaratoriska1.model.enumerations.BookCategory;
import com.example.labaratoriska1.service.AuthorService;
import com.example.labaratoriska1.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookRestController(BookService bookService,AuthorService authorService) {
        this.bookService = bookService;
        this.authorService=authorService;
    }

    @GetMapping
    private List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id)
    {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/pagination")
    public List<Book> findAllWithPagination(Pageable pageable){
        return this.bookService.findAllWithPagination(pageable).getContent();
    }

    @GetMapping("/categories")
    public List<BookCategory> allCategories(){
        List<BookCategory> result=new ArrayList<>();
        BookCategory bookCategory1=BookCategory.NOVEL;
        BookCategory bookCategory2=BookCategory.THRILER;
        BookCategory bookCategory3=BookCategory.HISTORY;
        BookCategory bookCategory4=BookCategory.FANTASY;
        BookCategory bookCategory5=BookCategory.BIOGRAPHY;
        BookCategory bookCategory6=BookCategory.CLASSICS;
        BookCategory bookCategory7=BookCategory.DRAMA;
        result.add(bookCategory1);
        result.add(bookCategory2);
        result.add(bookCategory3);
        result.add(bookCategory4);
        result.add(bookCategory5);
        result.add(bookCategory6);
        result.add(bookCategory7);
        return result;
    }

    @GetMapping("/authors")
    public List<Author> findAllAuthors()
    {
        return this.authorService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto)
    {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PostMapping("/marktaken/{id}")
    public ResponseEntity markAsTaken(@PathVariable Long id)
    {
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.badRequest().build();
        bookService.markAsTaken(id);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id)
    {
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id,@RequestBody  BookDto bookDto)
    {
        return this.bookService.edit(id,bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

}
