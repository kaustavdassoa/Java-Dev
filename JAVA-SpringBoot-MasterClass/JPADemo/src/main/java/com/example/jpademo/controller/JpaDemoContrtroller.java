package com.example.jpademo.controller;


import com.example.jpademo.domain.Category;
import com.example.jpademo.repositories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping
public class JpaDemoContrtroller {

    CategoryRepository categoryRepository;

    public JpaDemoContrtroller(CategoryRepository categoryRepository)
    {
        this.categoryRepository=categoryRepository;
    }


    @GetMapping(path = "/getCategory")
    public ResponseEntity<Category> getCategory(@RequestParam String description)
    {
        String categoryID="";

        Optional<Category> categories=categoryRepository.findByDescription(description);
        if(categories.isPresent())
        {
            categoryID=categories.get().getId().toString();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categories.get());
    }
}
