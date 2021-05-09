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

    @ManyToOne
    private static List<Book> lists = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name="user_id")
    private static List<User> users= new ArrayList<>();

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

    public static List<Book> getLists() {
        return lists;
    }

    public static void setLists(List<Book> lists) {
        BookLists.lists = lists;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        BookLists.users = users;
    }
}
