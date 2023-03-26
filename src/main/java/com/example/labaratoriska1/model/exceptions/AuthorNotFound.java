package com.example.labaratoriska1.model.exceptions;

public class AuthorNotFound extends RuntimeException{

    public AuthorNotFound(Long id) {
        super(String.format("Author with id: %d not found!",id ));
    }
}
