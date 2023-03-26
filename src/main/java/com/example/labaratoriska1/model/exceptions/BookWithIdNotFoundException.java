package com.example.labaratoriska1.model.exceptions;

public class BookWithIdNotFoundException extends RuntimeException {

    public BookWithIdNotFoundException(Long id) {
        super(String.format("Book with id: %d not found",id));
    }
}
