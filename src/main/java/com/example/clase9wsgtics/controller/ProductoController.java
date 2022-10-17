package com.example.clase9wsgtics.controller;

import com.example.clase9wsgtics.entity.Product;
import com.example.clase9wsgtics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ProductoController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/prueba")
    public String index() {
        return "hola";
    }

    @GetMapping("/product")
    public List<Product> listaProductos() {
        return productRepository.findAll();
    }

    @GetMapping("/producto/buscar1")
    public Product buscarProductoForma1(@RequestParam("id") String id) {
        try {
            int idBuscar = Integer.parseInt(id);
            Optional<Product> byId = productRepository.findById(idBuscar);
            if (byId.isPresent()) {
                return byId.get();
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @GetMapping(value = "/producto/buscar2")
    public HashMap<String,Object> buscarProductoForma2(@RequestParam("id") String id) {
        HashMap<String,Object> hashMap = new HashMap<>();
        /*
            quiero devolver
            {
                existe: true/false
                msg: "mensaje" (solo si es false)
                producto: { producto }
            }
         */
        try {
            int idBuscar = Integer.parseInt(id);
            Optional<Product> byId = productRepository.findById(idBuscar);
            if (byId.isPresent()) {
                hashMap.put("existe",true);
                hashMap.put("producto",byId.get());
            } else {
                hashMap.put("existe",false);
                hashMap.put("msg","El producto con ese ID no existe");
            }
        } catch (NumberFormatException e) {
            hashMap.put("existe",false);
            hashMap.put("msg","El id no es un número!!!");
        }
        return hashMap;
    }

}
