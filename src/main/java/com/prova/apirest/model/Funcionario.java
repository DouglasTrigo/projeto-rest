package com.prova.apirest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "funcionario")
@SequenceGenerator(name = "funcionario_seq", sequenceName = "funcionario_seq", initialValue = 1, allocationSize = 1)
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_seq")
    @Column(name = "funcionario_id")
    private Long id;

    @Column(name = "funcionario_name")
    private String nome;

    @Column(name = "funcionario_age")
    private Integer idade;

    @Column(name = "funcionario_birthday")
    private LocalDate aniversario;

    @Column(name = "funcionario_document")
    private String documento;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany
    @JoinColumn(name = "funcionario_id")
    private List<HistoricoDepartamento> historicoDepartamentos;

    @Column(name = "removido")
    private Integer removido;

    public void adicionarHistoricoDepartamento(HistoricoDepartamento historicoDepartamento){
        if(historicoDepartamentos == null){
            historicoDepartamentos = new ArrayList<>();
        }

        historicoDepartamentos.add(historicoDepartamento);
    }
}
