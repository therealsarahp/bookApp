package com.example.BookListApp.Models.Data;

import com.example.BookListApp.Models.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author findByName(String name);

}
