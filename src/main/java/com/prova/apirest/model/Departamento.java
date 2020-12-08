package com.prova.apirest.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departamento_id")
    private Long id;

    @Column(name = "departamento_name")
    @NotNull
    private String nome;

    @OneToMany
    private List<Funcionario> funcionarios;

    @OneToOne
    @JoinColumn(name = "chefe")
    private Funcionario chefe;
}
