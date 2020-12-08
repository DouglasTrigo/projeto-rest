package com.prova.apirest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cargo_id")
    private Long id;

    @Column(name = "cargo_name")
    private String nome;

    @OneToMany
    private List<Funcionario> funcionarios;
}
