create table funcionario_historico_departamento (
    id int primary key auto_increment,
    funcionario_id int not null,
    data date not null,
    departamento_id int not null,
    constraint fk_hist_func foreign key (funcionario_id)
        references funcionario(funcionario_id),
    constraint fk_hist_dept foreign key (departamento_id)
        references departamento(departamento_id)
);

CREATE SEQUENCE func_hist_dept_seq
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1;