package com.example.labaratoriska1.config;

import com.example.labaratoriska1.model.Author;
import com.example.labaratoriska1.model.Country;
import com.example.labaratoriska1.model.enumerations.BookCategory;
import com.example.labaratoriska1.service.AuthorService;
import com.example.labaratoriska1.service.BookService;
import com.example.labaratoriska1.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private  final CountryService countryService;
    private final BookService bookService;
    private final AuthorService authorService;

    public DataInitializer(CountryService countryService, BookService bookService, AuthorService authorService) {
        this.countryService = countryService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostConstruct
    public void initData()
    {
        Country country=this.countryService.create("Macedonia","Europe");
        Author author=this.authorService.create("Author1","surname",country);
        this.bookService.create("Book1", BookCategory.DRAMA,author,2);
        this.bookService.create("Book2",BookCategory.BIOGRAPHY,author,3);
        this.bookService.create("Book3",BookCategory.NOVEL,author,6);
        this.bookService.create("Book4",BookCategory.NOVEL,author,7);
        this.bookService.create("Book5",BookCategory.NOVEL,author,5);
        this.bookService.create("Book6",BookCategory.NOVEL,author,6);

    }
}
