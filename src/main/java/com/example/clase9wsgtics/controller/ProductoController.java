package com.example.clase9wsgtics.controller;

import com.example.clase9gtics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductoController {

    @Autowired
    ProductRepository productRepository;

}
