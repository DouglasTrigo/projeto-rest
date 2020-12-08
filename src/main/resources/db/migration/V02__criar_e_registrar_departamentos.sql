create table departamento (
    departamento_id int primary key,
    departamento_name varchar(50) not null
);

insert into departamento (departamento_id, departamento_name) values (1, 'Administrativo');
insert into departamento (departamento_id, departamento_name) values (2, 'Financeiro');
insert into departamento (departamento_id, departamento_name) values (3, 'Tecnológico');