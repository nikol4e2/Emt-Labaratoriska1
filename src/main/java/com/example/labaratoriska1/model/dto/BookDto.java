package com.example.labaratoriska1.model.dto;

import com.example.labaratoriska1.model.Author;
import com.example.labaratoriska1.model.enumerations.BookCategory;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BookDto {


    private String name;

    private BookCategory bookCategory;


    private Author author;

    private int availableCopies;

    public BookDto(String name, BookCategory bookCategory, Author author, int availableCopies) {
        this.name = name;
        this.bookCategory = bookCategory;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
