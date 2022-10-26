package com.example.lab7grupo3.repository;

import com.example.lab7grupo3.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
