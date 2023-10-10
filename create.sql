create table estoque (id bigserial not null, descricao varchar(255) not null, nome varchar(50) not null, primary key (id));
create table produto (id bigserial not null, descricao varchar(255) not null, imagem_url varchar(255) not null, nome varchar(50) not null, quantidade integer not null, quantidade_minima integer not null, estoque_id bigint not null, primary key (id));
alter table if exists produto add constraint FKckrfxh3w8vwieuo057pac8pdw foreign key (estoque_id) references estoque;
