package com.prova.apirest.service;

import com.prova.apirest.dto.request.ChefeRequest;
import com.prova.apirest.dto.response.FuncionarioResponse;
import com.prova.apirest.exception.RequestInvalidaException;
import com.prova.apirest.model.Departamento;
import com.prova.apirest.model.Funcionario;
import com.prova.apirest.repository.DepartamentoRepository;
import com.prova.apirest.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DepartamentoService {

    private DepartamentoRepository repository;
    private FuncionarioRepository funcionarioRepository;
    private FuncionarioService funcionarioService;

    public void definirChefe(Long idDepartamento, ChefeRequest request) {
        Departamento departamento = getDepartamento(idDepartamento);
        Funcionario funcionario = funcionarioService.consultaFuncionario(request.getFuncionarioId());

        if(funcionario.getDepartamento().getId() != idDepartamento){
            throw new RequestInvalidaException(List.of("O funcionário não pertence ao departamento"));
        }

        departamento.setChefe(funcionario);
        repository.save(departamento);
    }

    public List<FuncionarioResponse> getFuncionariosDoDepartamento(Long idDepartamento) {
        Departamento departamento = getDepartamento(idDepartamento);
        List<Funcionario> funcionarios = funcionarioRepository
                .findByDepartamentoAndRemovido(departamento, 0);

        if(ObjectUtils.isEmpty(funcionarios)){
            return new ArrayList<>();
        }

        return funcionarios
                .stream()
                .map(funcionario -> new FuncionarioResponse(funcionario))
                .collect(Collectors.toList());
    }

    private Departamento getDepartamento(Long idDepartamento){
        return repository
                .findById(idDepartamento)
                .orElseThrow(() -> new RequestInvalidaException(List.of("idDepartamento inválido")));
    }
}
