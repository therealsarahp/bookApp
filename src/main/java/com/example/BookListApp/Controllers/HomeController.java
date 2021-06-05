package com.example.BookListApp.Controllers;

import com.example.BookListApp.Models.Data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BookListsRepository bookListsRepository;



//    private User user= userRepository.findById(userSessionKey);

    @GetMapping("")
    public String displayBookList(Model model, HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "";
    }
//
//    @GetMapping("userHome")
//    public String displayUserHomePage(Model model, HttpServletRequest request, HttpServletResponse response){
//        model.addAttribute("user", request.getSession().getAttribute("username"));
//        return "userHome";
//    }
//


}
