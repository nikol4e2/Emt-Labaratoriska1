package com.example.labaratoriska1.model.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String name) {
        super(String.format("Book with name %s not found",name));
    }
}
