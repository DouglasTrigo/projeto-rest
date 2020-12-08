package com.prova.apirest.service;

import com.prova.apirest.client.AleatorioClient;
import com.prova.apirest.dto.response.AleatorioResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AleatorioServiceTest {

    @InjectMocks
    AleatorioService serviceMock;

    @Mock
    AleatorioClient clientMock;

    @Test
    public void testaQueOServicoRetornaOIdEsperado(){
        var response = new AleatorioResponse();
        response.setDeck_id("123456");

        when(clientMock.getAleatorioResponse(1)).thenReturn(response);
        assertEquals(clientMock.getAleatorioResponse(1), response);
    }

}
