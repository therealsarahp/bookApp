package com.example.BookListApp.Models.Data;

import com.example.BookListApp.Models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository <Category, Integer> {
}
