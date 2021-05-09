package com.example.BookListApp.Controllers;

import com.example.BookListApp.Models.Author;
import com.example.BookListApp.Models.Book;
import com.example.BookListApp.Models.Category;
import com.example.BookListApp.Models.Data.AuthorRepository;
import com.example.BookListApp.Models.Data.BookRepository;
import com.example.BookListApp.Models.Data.CategoryRepository;
import com.example.BookListApp.Models.dto.AuthorFormDTO;
import com.example.BookListApp.Models.dto.BookFormDTO;
import com.example.BookListApp.Models.dto.CategoryFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @GetMapping("")
    public String displayBookList(Model model){
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }




}
