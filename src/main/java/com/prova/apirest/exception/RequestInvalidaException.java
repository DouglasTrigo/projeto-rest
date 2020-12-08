package com.prova.apirest.exception;

import lombok.Getter;

import java.util.List;

public class RequestInvalidaException extends RuntimeException{

    @Getter
    private List<String> validacoes;

    public RequestInvalidaException(List<String> validacoes){
        this.validacoes = validacoes;
    }
}
