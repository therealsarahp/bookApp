package com.example.BookListApp.Models.Data;

import com.example.BookListApp.Models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository <Book, Integer>{
}
