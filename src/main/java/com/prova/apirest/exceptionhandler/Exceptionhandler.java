package com.prova.apirest.exceptionhandler;

import com.prova.apirest.exception.RequestInvalidaException;
import com.prova.apirest.exception.ResultadoNaoEcontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class Exceptionhandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(RequestInvalidaException.class)
    public ResponseEntity<Erro> handleRequestInvalidaException(RequestInvalidaException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Erro(getMensagemUsuario(exception.getValidacoes()), null));
    }

    private String getMensagemUsuario(List<String> mensagens){
        if(ObjectUtils.isEmpty(mensagens)){
            return null;
        }

        String mensagem = "";
        for(String msg : mensagens){
            mensagem += msg + " ";
        }

        return mensagem;
    }

    @ExceptionHandler(ResultadoNaoEcontradoException.class)
    public ResponseEntity<Erro> handleResultadoNaoEcontradoException(ResultadoNaoEcontradoException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Erro("id inválido", "id inválido"));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    public List<Erro> criarListaDeErros(BindingResult bindingResult){
        List<Erro> erros = new ArrayList<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();

            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }

    @Getter @Setter @AllArgsConstructor
    public static class Erro {

        private String mensagemUsuario;
        private String mensagemDesenvolvedor;
    }
}
