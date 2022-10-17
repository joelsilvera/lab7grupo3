package com.example.clase9wsgtics.controller;

import com.example.clase9wsgtics.entity.Supplier;
import com.example.clase9wsgtics.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    SupplierRepository supplierRepository;
    @GetMapping("/supplier")
    public List<Supplier> supplierList(){
        return supplierRepository.findAll();
    }
}
