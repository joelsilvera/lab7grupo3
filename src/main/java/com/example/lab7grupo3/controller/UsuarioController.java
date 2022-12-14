package com.example.lab7grupo3.controller;

import com.example.lab7grupo3.entity.Usuario;
import com.example.lab7grupo3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionarExcepcion(HttpServletRequest request){
        HashMap<String,String> hashMap = new HashMap<>();
        if(request.getMethod().equals("POST")){
            hashMap.put("error","true");
            hashMap.put("msg","Debes enviar el usuario como json");
        }
        return ResponseEntity.badRequest().body(hashMap);
    }


}
