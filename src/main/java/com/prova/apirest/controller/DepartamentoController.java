package com.prova.apirest.controller;

import com.prova.apirest.dto.request.ChefeRequest;
import com.prova.apirest.dto.response.FuncionarioResponse;
import com.prova.apirest.service.DepartamentoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departamentos")
@AllArgsConstructor
public class DepartamentoController {

    private DepartamentoService service;

    @ApiOperation(value =  "Define o chefe do departamento")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{idDepartamento}/chefe")
    public void cadastraChefe(@PathVariable Long idDepartamento, @RequestBody ChefeRequest request){
        service.definirChefe(idDepartamento, request);
    }

    @ApiOperation(value =  "Lista os funcionarios do departamento")
    @GetMapping("/{idDepartamento}/funcionarios")
    public List<FuncionarioResponse> funcionariosDoDepartamento(@PathVariable Long idDepartamento){
        return service.getFuncionariosDoDepartamento(idDepartamento);
    }
}
