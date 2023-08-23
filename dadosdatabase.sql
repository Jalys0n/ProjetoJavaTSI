create table Cliente(
    id int not null auto_increment,
    nome varchar(100) not null,
    telefone varchar(50) not null,
    endereco varchar (200) not null,
    data_Cadastro date not null,
    pontuacaoCliente decimal(5,2),
    pedidos varchar(10000),
    primary key (id)
);
