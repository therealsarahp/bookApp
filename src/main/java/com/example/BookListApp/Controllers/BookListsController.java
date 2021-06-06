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
import java.util.List;
import java.util.Optional;

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
    public String processAddBookList(@ModelAttribute @Valid BookLists bookLists, Errors errors, Model model) {
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
            return "bookLists/addBooks";
        }
    }

    @GetMapping("addBooks")
    public String viewAddBooksToList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("bookList", bookListsRepository.findAll());
        return "bookLists/addBooks";
    }

    @PostMapping("addBooks")
    public String processAddBooksToList(@RequestParam int bookListId, @RequestParam List<Integer> booksToAdd, Model model) {
        model.addAttribute("books", bookRepository.findAll());

            BookLists bookList = bookListsRepository.findById(bookListId).orElse(new BookLists());
            bookList.setListsOfBooks((List<Book>) bookRepository.findAllById(booksToAdd));

            return "redirect:";
        }


}