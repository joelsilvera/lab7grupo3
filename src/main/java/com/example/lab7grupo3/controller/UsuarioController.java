package com.example.lab7grupo3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category")
    public List<Category> categoryList(){
        return categoryRepository.findAll();
    }
}
