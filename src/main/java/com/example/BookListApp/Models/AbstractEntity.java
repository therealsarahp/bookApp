package com.example.BookListApp.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

    public AbstractEntity (){}

    public int getId() {
        return id;
    }
}
