package com.example.lab7grupo3.controller;

import com.example.lab7grupo3.entity.Solicitud;
import com.example.lab7grupo3.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitudes")

public class SolicitudesController {

    @Autowired
    SolicitudRepository solicitudRepository;






}
