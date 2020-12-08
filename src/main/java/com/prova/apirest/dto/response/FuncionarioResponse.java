package com.prova.apirest.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prova.apirest.model.Funcionario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class FuncionarioResponse {

    public FuncionarioResponse(Funcionario funcionario){
        BeanUtils.copyProperties(funcionario, this);
        this.cargo = new CargoResponse();
        BeanUtils.copyProperties(funcionario.getCargo(), this.cargo);
        this.departamento = new DepartamentoResponse();
        BeanUtils.copyProperties(funcionario.getDepartamento(), this.departamento);
    }

    private Long id;

    private String nome;

    private Integer idade;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate aniversario;

    private String documento;

    private CargoResponse cargo;

    private DepartamentoResponse departamento;

}
