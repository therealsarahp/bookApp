package com.example.BookListApp.Controllers;

import com.example.BookListApp.Models.Book;
import com.example.BookListApp.Models.BookLists;
import com.example.BookListApp.Models.Data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("bookLists")
public class BookListsController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookListsRepository bookListsRepository;

    @GetMapping
    public String displayAllBooks(Model model) {
        model.addAttribute("bookList", bookListsRepository.findAll());
        return "bookLists/index";
    }

    @GetMapping("add")
    public String viewAddBookList(Model model) {
        model.addAttribute(new BookLists());
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("bookList", bookListsRepository.findAll());
        return "bookLists/add";
    }

    @PostMapping("add")
    public String processAddBookList(@ModelAttribute @Valid BookLists bookLists, Errors errors, Model model,
                                     @RequestParam int bookListId, @ModelAttribute @Valid Book book) {
        if (errors.hasErrors()) {
            return "bookLists/add";
        }
        BookLists existingBookLists = bookListsRepository.findByName(bookLists.getName());

        if (existingBookLists != null) {
            errors.rejectValue("name", "name.alreadyexists", "A BookList with that name already exists");
            return "bookLists/add";
        } else {
            BookLists newBookLists = new BookLists(bookLists.getName());
            bookListsRepository.save(newBookLists);
            bookListId = newBookLists.getId();
            return "redirect:/add/${bookListId}";


        }


    }
}