package com.example.lab7grupo3.controller;

import com.example.lab7grupo3.entity.Pago;
import com.example.lab7grupo3.entity.Solicitud;
import com.example.lab7grupo3.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {
    @Autowired
    SolicitudRepository solicitudRepository;

    @PostMapping("/registro")
    public ResponseEntity<HashMap<String, String>> registrarSolicitud(@RequestBody Solicitud solicitud) {
        HashMap<String, String> hashMap = new HashMap<>();

        solicitudRepository.save(solicitud);

        hashMap.put("Monto solicitado", String.valueOf(solicitud.getSolicitudMonto()));
        hashMap.put("id", String.valueOf(solicitud.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }

    @PutMapping("/aprobarSolicitud")
    public ResponseEntity<HashMap<String, String>> aprobarSolicitud(@RequestParam("idSolicitud") String idSol) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            Optional<Solicitud> solicitudOpt = solicitudRepository.findById(Integer.parseInt(idSol));
            if(solicitudOpt.isPresent()){
                Solicitud solicitud =solicitudOpt.get();
                if(solicitud.getSolicitudEstado().equals("")){
                    solicitud.setSolicitudEstado("aprobado");
                    solicitudRepository.save(solicitud);
                    hashMap.put("id solicitud", idSol);
                }else{
                    hashMap.put("solicitud ya atendida", idSol);
                }
            }else{
                hashMap.put("msg", "La solicitud con este id no existe");
            }
        }catch (NumberFormatException e){
            hashMap.put("msg", "Debe ingresar un número entero positivo");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }

    @PutMapping("/denegarSolicitud")
    public ResponseEntity<HashMap<String, String>> denegarSolicitud(@RequestParam("idSolicitud") String idSol) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            Optional<Solicitud> solicitudOpt = solicitudRepository.findById(Integer.parseInt(idSol));
            if(solicitudOpt.isPresent()){
                Solicitud solicitud =solicitudOpt.get();
                if(solicitud.getSolicitudEstado().equals("")){
                    solicitud.setSolicitudEstado("denegada");
                    solicitudRepository.save(solicitud);
                    hashMap.put("id solicitud", idSol);
                }else{
                    hashMap.put("solicitud ya atendida", idSol);
                }
            }else{
                hashMap.put("msg", "La solicitud con este id no existe");
            }
        }catch (NumberFormatException e){
            hashMap.put("msg", "Debe ingresar un número entero positivo");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }


    @DeleteMapping("/borrarSolicitud")
    public ResponseEntity<HashMap<String, String>> borrarSolicitud(@RequestParam("idSolicitud") String idSol) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            Optional<Solicitud> solicitudOpt = solicitudRepository.findById(Integer.parseInt(idSol));
            if(solicitudOpt.isPresent()){
                Solicitud solicitud =solicitudOpt.get();
                if(solicitud.getSolicitudEstado().equals("denegada")){
                    solicitudRepository.deleteById(Integer.parseInt(idSol));
                    hashMap.put("id solicitud borrada", idSol);
                }else{
                    hashMap.put("msg", "Solo se puede eliminar las solicitudes denegadas");
                }
            }else{
                hashMap.put("msg", "La solicitud con este id no existe");
            }
        }catch (NumberFormatException e){
            hashMap.put("msg", "Debe ingresar un número entero positivo");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionarExcepcion(HttpServletRequest request) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            hashMap.put("error", "true");
            hashMap.put("msg", "Debes enviar el pago como json");
        }
        return ResponseEntity.badRequest().body(hashMap);
    }
}
