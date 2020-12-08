package com.prova.apirest.controller;

import com.prova.apirest.client.AleatorioClient;
import com.prova.apirest.dto.response.AleatorioResponse;
import com.prova.apirest.service.AleatorioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aleatorio")
@AllArgsConstructor
public class AleatorioController {

    private AleatorioService service;

    @GetMapping
    public ResponseEntity<AleatorioResponse> consulta(){
        return ResponseEntity.ok(service.get());
    }
}
