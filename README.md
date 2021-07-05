# API para catálogo de produtos
## _API para cadastro, atualização, pesquisa customizada e deleção de produtos._

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Neste microserviço é possível criar, alterar, visualizar e excluir um determinado produto, além de visualizar a lista de produtos atuais disponíveis. Também é possível realizar a busca de produtos filtrando por name, description e price.

## Detalhes
- [x] Banco de dados = PostgreSQL
- [x] Database name = compassoproducts user=postgres pass=postgres
- [x] Não é necessário criar a tabela no db, Utilizando Flyway
- [x] Servidor de aplicação porta = 9999

## Features
- [x] Cadastro de produto
- [x] Buscar todos os produtos cadastrados
- [x] Atualização de produto
- [x] Deletar um produto
- [x] Pesquisar produto por, nome, descrição e preço mínimo/máximo
## Endpoints
| Verbo HTTP | ResourcePath     | Descrição                              |
|------------|------------------|----------------------------------------|
| POST       | /products        | Criação de um produto                  |
| PUT        | /products/{ID}   | Atualização de um produto              |
| GET        | /products/{ID}   | Busca de um produto por ID             |
| GET        | /products        | Lista de todos os produtos Cadastrados |
| GET        | /products/search | Lista de produtos filtrados            |
| DELETE     | /products/{ID}   | Deleção de um produto por ID           |
