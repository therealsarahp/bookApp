package com.example.BookListApp.Models.dto;

import com.example.BookListApp.Models.Book;
import com.example.BookListApp.Models.Category;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CategoryFormDTO {


    @NotBlank(message = "Name is required")
    private String name;

    @OneToMany
    @JoinColumn(name="category_id")
    private static List<Book> books = new ArrayList<>();

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
