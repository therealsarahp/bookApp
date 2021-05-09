package com.example.BookListApp.Models.dto;

import com.example.BookListApp.Models.Author;
import com.example.BookListApp.Models.Category;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookFormDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 240, message = "Invalid Title")
    private String title;

    @NotBlank(message="Author is Required")
    @OneToMany
    @JoinColumn(name="author_id")
    private Author author;

    private Category category;

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
