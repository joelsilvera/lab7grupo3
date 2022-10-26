package com.example.lab7grupo3.controller;

import com.example.lab7grupo3.entity.Pago;
import com.example.lab7grupo3.entity.Usuario;
import com.example.lab7grupo3.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {
    @Autowired
    PagoRepository pagoRepository;

    @GetMapping("/listarPagos")
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    @PostMapping("/registrarPago")
    public ResponseEntity<HashMap<String, String>> regustrarPago(@RequestBody Pago pago) {
        HashMap<String, String> hashMap = new HashMap<>();

        pagoRepository.save(pago);

        hashMap.put("id", String.valueOf(pago.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionarExcepcion(HttpServletRequest request) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (request.getMethod().equals("POST")) {
            hashMap.put("error", "true");
            hashMap.put("msg", "Debes enviar el pago como json");
        }
        return ResponseEntity.badRequest().body(hashMap);
    }
}