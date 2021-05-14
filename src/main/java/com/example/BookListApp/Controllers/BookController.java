package com.example.BookListApp.Controllers;

import com.example.BookListApp.Models.Book;
import com.example.BookListApp.Models.Data.AuthorRepository;
import com.example.BookListApp.Models.Data.BookRepository;
import com.example.BookListApp.Models.Data.CategoryRepository;
import com.example.BookListApp.Models.dto.BookFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("add")
    public String viewAddBookForm(Model model){
        model.addAttribute(new BookFormDTO());
        model.addAttribute("categories", categoryRepository.findAll());
        return "books/add";
    }

    @PostMapping("add")
    public String processAddBookForm(@ModelAttribute @Valid BookFormDTO bookFormDTO, Model model,
                                     Errors errors){

        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        if(errors.hasErrors()){
            return "books/add";
        }
        Book existingBook = bookRepository.findByTitle(bookFormDTO.getTitle());

        if(existingBook != null){
            errors.rejectValue("title", "title.alreadyexists", "A book with that title already exists.");
            return "books/add";
        } else{
            Book newBook = new Book(bookFormDTO.getTitle(), bookFormDTO.getAuthor());
            bookRepository.save(newBook);
            return "redirect:";

        }
    }

    @GetMapping("view/{bookId}")
    public String displayViewBook(Model model, @PathVariable int bookId) {
        Optional<Book> result = bookRepository.findById(bookId);
        if (result.isPresent()) {
            Book book = result.get();
            model.addAttribute("book", book);
            return "books/view";
        } else {
            return "books/index";
        }

    }

    @GetMapping
    public String displayAllBooks(Model model){
        model.addAttribute("title", "All Books");
        model.addAttribute("books", bookRepository.findAll());
        return "books/index";
    }
}
