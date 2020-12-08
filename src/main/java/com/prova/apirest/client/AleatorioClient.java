package com.prova.apirest.client;

import com.prova.apirest.dto.response.AleatorioResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AleatorioClient {

    public AleatorioResponse getAleatorioResponse(Integer deckCount){
        var restTemplate = new RestTemplate();
        String url = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=" + deckCount;
        return restTemplate.getForEntity(url, AleatorioResponse.class).getBody();
    }
}
