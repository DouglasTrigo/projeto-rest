package com.prova.apirest.service;

import com.prova.apirest.dto.request.CargoRequest;
import com.prova.apirest.dto.request.DepartamentoRequest;
import com.prova.apirest.dto.request.FuncionarioRequest;
import com.prova.apirest.dto.response.FuncionarioResponse;
import com.prova.apirest.exception.RequestInvalidaException;
import com.prova.apirest.exception.ResultadoNaoEcontradoException;
import com.prova.apirest.model.Cargo;
import com.prova.apirest.model.Departamento;
import com.prova.apirest.model.Funcionario;
import com.prova.apirest.repository.DepartamentoRepository;
import com.prova.apirest.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

    @InjectMocks
    FuncionarioService serviceMock;

    @Mock
    DepartamentoRepository departamentoRepositoryMock;

    @Mock
    FuncionarioRepository funcionarioRepositoryMock;

    @Test
    public void testaSeAoSalvarValidaODepartamento(){
        when(departamentoRepositoryMock.findById(1l)).thenReturn(Optional.empty());

        var request = new FuncionarioRequest();
        request.setNome("João");
        request.setAniversario(LocalDate.now());
        request.setCargo(getCargoRequest(1l));
        request.setDepartamento(getDepartamentoRequest(1l));

        assertThrows(RequestInvalidaException.class, () -> {
            serviceMock.salvar(request);
        });
    }

    @Test
    public void testaQueNaoEncontraFuncionario(){
        when(funcionarioRepositoryMock.findById(98l)).thenReturn(Optional.empty());

        assertThrows(ResultadoNaoEcontradoException.class, () -> {
            serviceMock.consultaFuncionario(98l);
        });

        var funcionarioRemovido = new Funcionario();
        funcionarioRemovido.setId(99l);
        funcionarioRemovido.setRemovido(1);
        when(funcionarioRepositoryMock.findById(99l)).thenReturn(Optional.of(funcionarioRemovido));

        assertThrows(ResultadoNaoEcontradoException.class, () -> {
            serviceMock.consultaFuncionario(99l);
        });
    }

    @Test
    public void testaQueEncontraFuncionario(){
        var funcionarioEncontrado = new Funcionario();
        funcionarioEncontrado.setId(1l);
        funcionarioEncontrado.setRemovido(0);
        funcionarioEncontrado.setCargo(new Cargo());
        funcionarioEncontrado.setDepartamento(new Departamento());

        when(funcionarioRepositoryMock.findById(1l)).thenReturn(Optional.of(funcionarioEncontrado));
        assertEquals(serviceMock.findFuncionarioResponse(1l), new FuncionarioResponse(funcionarioEncontrado));
    }

    private CargoRequest getCargoRequest(Long id){
        var cargo = new CargoRequest();
        cargo.setId(id);
        return cargo;
    }

    private DepartamentoRequest getDepartamentoRequest(Long id){
        var departamento = new DepartamentoRequest();
        departamento.setId(id);
        return departamento;
    }
}
