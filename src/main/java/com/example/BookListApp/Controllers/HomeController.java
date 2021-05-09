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
//        model.addAttribute("book", new Book("Barbara" ,"fakeAuthor"));
        return "index";
    }

    @GetMapping("addAuthor")
    public String viewAddAuthorForm(Model model){
        model.addAttribute(new AuthorFormDTO());
        return "addAuthor";

    }

    @PostMapping("addAuthor")
    public String processAddAuthorForm(@ModelAttribute @Valid AuthorFormDTO authorFormDTO, Model model,
                                       Errors errors){
        if(errors.hasErrors()){
            return "addAuthor";
        }
        Author existingAuthor = authorRepository.findByName(authorFormDTO.getName());

        if(existingAuthor != null){
            errors.rejectValue("name", "name.alreadyexists", "An Author With That Name Already Exists.");
            return "addAuthor";
        } else{
            Author newAuthor= new Author(authorFormDTO.getName());
            authorRepository.save(newAuthor);
            return "redirect: ";
        }
    }

    @GetMapping("addBook")
    public String viewAddBookForm(Model model){
        model.addAttribute(new BookFormDTO());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addBook";
    }

    @PostMapping("addBook")
    public String processAddBookForm(@ModelAttribute @Valid BookFormDTO bookFormDTO, Model model,
                                     Errors errors){
        if(errors.hasErrors()){
            return "addBook";
        }
        Book existingBook = bookRepository.findByTitle(bookFormDTO.getTitle());

        if(existingBook != null){
            errors.rejectValue("title", "title.alreadyexists", "A book with that title already exists.");
            return "addBook";
        } else{
            Book newBook = new Book(bookFormDTO.getTitle(), bookFormDTO.getAuthor());
            bookRepository.save(newBook);
            return "index";

        }
    }

    @GetMapping("addCategory")
    public String viewAddCategoryForm(Model model){
        model.addAttribute(new CategoryFormDTO());
        return "addCategory";
    }

    @PostMapping("addCategory")
    public String processAddCategoryForm(@ModelAttribute @Valid CategoryFormDTO categoryFormDTO, Model model,
                                         Errors errors){
        if(errors.hasErrors()){
            return "addCategory";
        }
        Category existingCategory = new Category(categoryFormDTO.getName());

        if(existingCategory!= null){
            errors.rejectValue("name", "category.alreadyexists", "A Category with that Name Already Exists." );
            return "addCategory";
        } else{
            Category newCategory = new Category(categoryFormDTO.getName());
            categoryRepository.save(newCategory);
            return "redirect: ";
        }

    }



}
