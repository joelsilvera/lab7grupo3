package com.example.lab7grupo3.controller;

import com.example.lab7grupo3.entity.Accion;
import com.example.lab7grupo3.entity.Pago;
import com.example.lab7grupo3.repository.AccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class AccionController {
    @Autowired
    AccionRepository accionRepository;

    @PostMapping("/save")
    public ResponseEntity<HashMap<String,String>> guardarAccion(@RequestBody Accion accion){
        HashMap<String,String> hashMap = new HashMap<>();

        accionRepository.save(accion);

        hashMap.put("idCreado", String.valueOf(accion.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionarExcepcion(HttpServletRequest request){
        HashMap<String,String> hashMap = new HashMap<>();
        if(request.getMethod().equals("POST")){
            hashMap.put("error","true");
            hashMap.put("msg","Debes enviar la accion como json");
        }
        return ResponseEntity.badRequest().body(hashMap);
    }
}
