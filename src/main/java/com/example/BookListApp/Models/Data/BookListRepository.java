package com.example.BookListApp.Models.Data;

import com.example.BookListApp.Models.BookLists;
import com.example.BookListApp.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookListRepository extends CrudRepository<BookLists, Integer> {

//    List findByUsername(User.findByUsername(String name));

    BookLists findByName(String name);
    BookLists findByUser(User user_id);

}
