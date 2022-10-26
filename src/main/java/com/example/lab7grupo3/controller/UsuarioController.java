package com.example.lab7grupo3.controller;

import com.example.lab7grupo3.entity.Usuario;
import com.example.lab7grupo3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<HashMap<String,String>> crearUsuario(@RequestBody Usuario usuario){
        HashMap<String,String> hashMap = new HashMap<>();

        usuarioRepository.save(usuario);

        hashMap.put("id creado", String.valueOf(usuario.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }


}
