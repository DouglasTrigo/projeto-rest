alter table departamento add chefe int;

alter table departamento
add foreign key (chefe)
references funcionario(funcionario_id);