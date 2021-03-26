package com.example.BookListApp.Models;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book extends AbstractEntity{

    @NotBlank(message="Title Is Required")
    private String title;

    @NotBlank(message="Author is Required")
    private String author;

    @OneToMany
    @JoinColumn(name="category_id")
    private List<Category> categories= new ArrayList<>();

    public Book(String title, String author) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
