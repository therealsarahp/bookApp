package com.example.BookListApp.Models.Data;

import com.example.BookListApp.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
