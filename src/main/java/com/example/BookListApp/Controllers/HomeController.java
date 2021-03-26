package com.example.BookListApp.Controllers;

import com.example.BookListApp.Models.Book;
import com.example.BookListApp.Models.Category;
import com.example.BookListApp.Models.Data.BookRepository;
import com.example.BookListApp.Models.Data.CategoryRepository;
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


    @GetMapping("")
    public String displayBookList(Model model){
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
//        model.addAttribute("book", new Book("Barbara" ,"fakeAuthor"));
        return "index";
    }

    @GetMapping("addBook")
    public String viewAddBookForm(Model model){
        model.addAttribute(new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addBook";
    }

    @PostMapping("addBook")
    public String processAddBookForm(@ModelAttribute @Valid Book newBook, Model model,
                                     Errors errors){
        if(errors.hasErrors()){
            return "addBook";
        } else{
            model.addAttribute(bookRepository.save(newBook));
        }
        return "redirect: ";
    }

    @GetMapping("addCategory")
    public String viewAddCategoryForm(Model model){
        model.addAttribute(new Category());
        return "addCategory";
    }

    @PostMapping("addCategory")
    public String processAddCategoryForm(@ModelAttribute @Valid Category newCategory, Model model,
                                         Errors errors){
        if(errors.hasErrors()){
            return "addCategory";
        } else{
            model.addAttribute(categoryRepository.save(newCategory));
        }
        return "redirect: ";
    }



}
