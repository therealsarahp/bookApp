package com.example.BookListApp.Controllers;


import com.example.BookListApp.Models.Author;
import com.example.BookListApp.Models.Category;
import com.example.BookListApp.Models.Data.CategoryRepository;
import com.example.BookListApp.Models.dto.CategoryFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("add")
    public String viewAddCategoryForm(Model model){
        model.addAttribute(new CategoryFormDTO());
        return "categories/add";
    }

    @PostMapping("add")
    public String processAddCategoryForm(@ModelAttribute @Valid CategoryFormDTO categoryFormDTO, Model model,
                                         Errors errors){
        if(errors.hasErrors()){
            return "categories/add";
        }
        Category existingCategory = categoryRepository.findByName(categoryFormDTO.getName());

        if(existingCategory!= null){
            errors.rejectValue("name", "category.alreadyexists", "A Category with that Name Already Exists." );
            return "categories/add";
        } else{
            Category newCategory = new Category(categoryFormDTO.getName());
            categoryRepository.save(newCategory);
            return "redirect: ";
        }

    }


    @GetMapping("view/{categoryId}")
    public String displayViewBook(Model model, @PathVariable int categoryId) {
        Optional<Category> result = categoryRepository.findById(categoryId);
        if (result.isPresent()) {
            Category category = result.get();
            model.addAttribute("category", category);
            return "categories/view";
        } else {
            return "redirect: ";
        }

    }

    @GetMapping
    public String displayAllCategories(Model model){
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories/index";
    }

}
