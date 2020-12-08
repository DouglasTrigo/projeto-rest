package com.prova.apirest.dto.request;

import com.prova.apirest.model.Cargo;
import com.prova.apirest.model.Departamento;
import com.prova.apirest.model.Funcionario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter @Setter
public class FuncionarioRequest {

    @NotNull
    @Size(min = 3, max = 50)
    private String nome;

    @NotNull
    private Integer idade;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate aniversario;

    @NotNull
    @Size(max = 50)
    private String documento;

    @NotNull
    private CargoRequest cargo;

    @NotNull
    private DepartamentoRequest departamento;

    public Funcionario getFuncionario(){
        Funcionario funcionario = new Funcionario();

        BeanUtils.copyProperties(this, funcionario);

        var cargo = new Cargo();
        cargo.setId(this.cargo.getId());
        funcionario.setCargo(cargo);

        var departamento = new Departamento();
        departamento.setId(this.departamento.getId());
        funcionario.setDepartamento(departamento);

        return funcionario;
    }

    public Departamento getDepartamentoDoFuncionario(){
        return departamento.getDepartamento();
    }
}
