package com.prova.apirest.repository;

import com.prova.apirest.model.Departamento;
import com.prova.apirest.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByDepartamentoAndRemovido(Departamento departamento,
                                                    Integer removido);

}
