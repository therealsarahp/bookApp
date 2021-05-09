package com.example.BookListApp.Controllers;


import com.example.BookListApp.Models.Author;
import com.example.BookListApp.Models.Book;
import com.example.BookListApp.Models.Data.AuthorRepository;
import com.example.BookListApp.Models.Data.BookRepository;
import com.example.BookListApp.Models.Data.CategoryRepository;
import com.example.BookListApp.Models.dto.AuthorFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("add")
    public String viewAddAuthorForm(Model model){
        model.addAttribute(new AuthorFormDTO());
        return "authors/add";

    }

    @PostMapping("add")
    public String processAddAuthorForm(@ModelAttribute @Valid AuthorFormDTO authorFormDTO, Model model,
                                       Errors errors){
        if(errors.hasErrors()){
            return "authors/add";
        }
        Author existingAuthor = authorRepository.findByName(authorFormDTO.getName());

        if(existingAuthor != null){
            errors.rejectValue("name", "name.alreadyexists", "An Author With That Name Already Exists.");
            return "authors/add";
        } else{
            Author newAuthor= new Author(authorFormDTO.getName());
            authorRepository.save(newAuthor);
            return "redirect: ";
        }
    }

    @GetMapping("view/{authorId}")
    public String displayViewBook(Model model, @PathVariable int authorId) {
        Optional<Author> result = authorRepository.findById(authorId);
        if (result.isPresent()) {
            Author author = result.get();
            model.addAttribute("Author", author);
            return "authors/view";
        } else {
            return "redirect../";
        }

    }

    @GetMapping
    public String displayAllAuthors(Model model){
        model.addAttribute("title", "All Authors");
        model.addAttribute("employers", authorRepository.findAll());
        return "authors/index";
    }

}
