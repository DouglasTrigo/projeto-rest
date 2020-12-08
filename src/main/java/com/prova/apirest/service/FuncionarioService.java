package com.prova.apirest.service;

import com.prova.apirest.dto.request.FuncionarioRequest;
import com.prova.apirest.dto.response.FuncionarioResponse;
import com.prova.apirest.exception.RequestInvalidaException;
import com.prova.apirest.exception.ResultadoNaoEcontradoException;
import com.prova.apirest.model.Funcionario;
import com.prova.apirest.model.HistoricoDepartamento;
import com.prova.apirest.repository.CargoRepository;
import com.prova.apirest.repository.DepartamentoRepository;
import com.prova.apirest.repository.FuncionarioRepository;
import com.prova.apirest.repository.HistoricoDepartamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FuncionarioService {

    private FuncionarioRepository repository;
    private DepartamentoRepository departamentoRepository;
    private HistoricoDepartamentoRepository historicoDepartamentoRepository;
    private CargoRepository cargoRepository;

    @Transactional
    public FuncionarioResponse salvar(FuncionarioRequest request){

        validaFunciorarioRequest(request);

        Funcionario funcionario = request.getFuncionario();
        funcionario.setRemovido(0);
        Funcionario funcionarioSalvo = repository.save(funcionario);

        var historicoDepartamento = new HistoricoDepartamento(funcionario);
        historicoDepartamentoRepository.save(historicoDepartamento);

        return findFuncionarioResponse(funcionarioSalvo.getId());
    }

    @Transactional
    public FuncionarioResponse atualizar(Long id, FuncionarioRequest request) {

        Funcionario funcionario = consultaFuncionario(id);

        validaFunciorarioRequest(request);

        if(request.getDepartamento().getId() != funcionario.getDepartamento().getId()){
            salvaHistoricoDepartamento(request);
        }

        BeanUtils.copyProperties(request, funcionario);
        BeanUtils.copyProperties(request.getCargo(), funcionario.getCargo());
        BeanUtils.copyProperties(request.getDepartamento(), funcionario.getDepartamento());
        repository.save(funcionario);

        return new FuncionarioResponse(funcionario);
    }

    private void validaFunciorarioRequest(FuncionarioRequest request){
        departamentoRepository
                .findById(request.getDepartamento().getId())
                .orElseThrow(() -> new RequestInvalidaException(List.of("idDepartamento inválido")));

        cargoRepository
                .findById(request.getCargo().getId())
                .orElseThrow(() -> new RequestInvalidaException(List.of("idCargo inválido")));
    }

    private void salvaHistoricoDepartamento(FuncionarioRequest request) {
        var historicoDepartamento = new HistoricoDepartamento();
        historicoDepartamento.setDepartamento(request.getDepartamentoDoFuncionario());
        historicoDepartamento.setFuncionario(request.getFuncionario());
        historicoDepartamentoRepository.save(historicoDepartamento);
    }

    public FuncionarioResponse findFuncionarioResponse(Long id){
        Funcionario funcionario = consultaFuncionario(id);
        return new FuncionarioResponse(funcionario);
    }

    public Funcionario consultaFuncionario(Long id){
        Funcionario funcionario = repository
                                    .findById(id)
                                    .orElseThrow(() -> new ResultadoNaoEcontradoException());
        if(funcionario.getRemovido() == 1){
            throw new ResultadoNaoEcontradoException();
        }

        return funcionario;
    }

    public void deletar(Long id) {
        Funcionario funcionario = consultaFuncionario(id);
        funcionario.setRemovido(1);
        repository.save(funcionario);
    }
}
