package com.example.BookListApp.Controllers;

import com.example.BookListApp.Models.Author;
import com.example.BookListApp.Models.Book;
import com.example.BookListApp.Models.Category;
import com.example.BookListApp.Models.Data.*;
import com.example.BookListApp.Models.User;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private BookListRepository bookListRepository;



//    private User user= userRepository.findById(userSessionKey);

    @GetMapping("")
    public String displayBookList(Model model, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @GetMapping("userHome")
    public String displayUserHomePage(Model model, HttpServletRequest request, HttpServletResponse response){
//            HttpSession session = request.getSession();
//        Integer userId= (Integer)session.getAttribute("user");
//        Optional<User> user = userRepository.findById(userId);
        String username = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getParameter("username");
        User user= userRepository.findByUsername(username);
        model.addAttribute("username", username);
//            model.addAttribute("bookLists", bookListRepository.findByUser(user));
        return "userHome";
    }



}
