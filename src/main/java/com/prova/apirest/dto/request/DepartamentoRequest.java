package com.prova.apirest.dto.request;

import com.prova.apirest.model.Departamento;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartamentoRequest {

    private Long id;

    public Departamento getDepartamento(){
        var departamento = new Departamento();
        departamento.setId(id);
        return departamento;
    }
}
