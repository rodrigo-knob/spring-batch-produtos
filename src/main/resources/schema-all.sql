DROP TABLE IF EXISTS produto;

create table produto
(
    id               serial,
    descricao             varchar(240),
    quantidadeEstoque      int,
    valor           float
)