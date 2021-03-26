package com.example.BookListApp.Models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends AbstractEntity{

    @NotBlank(message = "Name is required")
    private String name;

    @OneToMany
    @JoinColumn(name="category_id")
    private static List<Book> books = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public Category(){}

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
        this.books = books;
    }
}
