package com.example.BookListApp.Models;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author extends AbstractEntity{

    @NotBlank
    private String name;

    @OneToMany
    @JoinColumn(name="author_id")
    private static List<Book> books = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }

    public Author(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        Author.books = books;
    }
}