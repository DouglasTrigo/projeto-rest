create table funcionario (
    funcionario_id int primary key auto_increment,
    funcionario_name varchar(50) not null,
    funcionario_age int,
    funcionario_birthday date,
    funcionario_document varchar(50),
    cargo_id int not null,
    departamento_id int not null,
    removido int default 0,
    constraint fk_func_cargo foreign key (cargo_id)
        references cargo(cargo_id),
    constraint fk_func_dept foreign key (departamento_id)
        references departamento(departamento_id)
);

CREATE SEQUENCE funcionario_seq
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1;