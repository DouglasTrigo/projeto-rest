package com.prova.apirest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "funcionario_historico_departamento")
@SequenceGenerator(name = "func_hist_dept_seq", sequenceName = "func_hist_dept_seq", initialValue = 1, allocationSize = 1)
public class HistoricoDepartamento {

    public HistoricoDepartamento(Funcionario funcionario){
        this.funcionario = funcionario;
        this.departamento = funcionario.getDepartamento();
        this.data = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "func_hist_dept_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @Column(name = "data")
    private LocalDate data;

}
