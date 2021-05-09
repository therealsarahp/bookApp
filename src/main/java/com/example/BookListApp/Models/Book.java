package com.example.BookListApp.Models;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book extends AbstractEntity{

    @NotNull(message="title is required")
    @NotBlank(message="title is required")
    @Size(min = 3, max = 240, message = "Invalid Title")
    private String title;


    @NotNull(message="Author is Required")
    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToOne
//    @JoinColumn(name="category_id")
    private Category category;


    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public Book(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
