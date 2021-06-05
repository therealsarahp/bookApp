package com.example.BookListApp.Models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookLists extends AbstractEntity{

    @NotBlank(message="Please Name Your Book List")
    private String name;

    @ManyToMany
    @JoinColumn(name="book_id")
    private static List<Book> listsOfBooks = new ArrayList<>();

    @ManyToOne
//    @JoinColumn(name="user_id")
    private User user;

    public BookLists(String name) {
        this.name = name;
    }

    public BookLists() { };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Book> getListsOfBooks() {
        return listsOfBooks;
    }

    public static void setListsOfBooks(List<Book> lists) {
        BookLists.listsOfBooks = listsOfBooks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
