package com.prova.apirest.service;

import com.prova.apirest.client.AleatorioClient;
import com.prova.apirest.dto.response.AleatorioResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AleatorioService {

    private AleatorioClient client;

    public AleatorioResponse get(){
        return client.getAleatorioResponse(1);
    }

}
