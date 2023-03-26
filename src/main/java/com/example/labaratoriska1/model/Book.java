package com.example.labaratoriska1.model;

import com.example.labaratoriska1.model.enumerations.BookCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BookCategory bookCategory;

    @ManyToOne
    private Author author;

    private int availableCopies;

    public Book(String name, BookCategory bookCategory, Author author, int availableCopies) {
        this.name = name;
        this.bookCategory = bookCategory;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book() {
    }
}
