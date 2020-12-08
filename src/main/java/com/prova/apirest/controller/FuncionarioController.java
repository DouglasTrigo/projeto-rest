package com.prova.apirest.controller;

import com.prova.apirest.dto.request.FuncionarioRequest;
import com.prova.apirest.dto.response.FuncionarioResponse;
import com.prova.apirest.event.RecursoCriadoEvent;
import com.prova.apirest.service.FuncionarioService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/funcionarios")
@AllArgsConstructor
public class FuncionarioController {

    private FuncionarioService service;

    private ApplicationEventPublisher publisher;

    @ApiOperation(value =  "Insere um funcionário")
    @PostMapping
    public ResponseEntity<FuncionarioResponse> inserir(@Valid @RequestBody FuncionarioRequest request,
                                                       HttpServletResponse response){
        FuncionarioResponse funcionarioSalvo = service.salvar(request);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, funcionarioSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }

    @ApiOperation(value =  "Atualiza um funcionário")
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> atualizar(@PathVariable Long id, @Valid @RequestBody FuncionarioRequest request){
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @ApiOperation(value =  "Consulta funcionário por id")
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> consultaPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.findFuncionarioResponse(id));
    }

    @ApiOperation(value =  "Deleta um funcionário")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.deletar(id);
    }

}
