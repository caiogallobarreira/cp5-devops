# :package: Controle de Produto
### Objetivo do Projeto
Este projeto consiste em auxiliar E-commerces a controlarem seus produtos de maneira eficiente

## :rocket: How to - Deploy na Azure
1. Crie um Serviço de Aplicativo na Azure
   
    1.1. Selecione Aplicativo

    1.2. Selecione o Runtime Stack Java 17

    1.3. Selecione o Sistema Operacional Linux

2. Altere o arquivo `application.properties` com as informações do banco de dados (MySQL, PostgreSQL, Oracle) que está localizado na pasta `src/main/resources`

3. Faça o deploy do projeto na Azure

    3.1. Utilize o plugin da Azure em alguma IDE (IntelliJ, Eclipse, VSCode) ou utilize a Azure CLI

4. Acesse a URL do projeto gerado pela Azure

## 🌐 Endpoints
- Estoque `/api/v1/estoque`
    - [Cadastrar estoque](#cadastrar-estoque)
    - [Listar todos](#listar-todos)
    - [Detalhar estoque](#detalhar-estoque)
    - [Apagar estoque](#apagar-estoque)
    - [Atualizar estoque](#atualizar-estoque)
- Produto `/api/v1/produto`
    - [Cadastrar produto](#cadastrar-produto)
    - [Listar todas](#listar-todas)
    - [Detalhar produto](#detalhar-produto)
    - [Estoque mínimo](#estoque-mínimo)
    - [Apagar produto](#apagar-produto)
    - [Atualizar produto](#atualizar-produto)

## :package: Estoque

### Listar todos

`GET` /api/v1/estoque

**Exemplo de corpo da resposta**

```js
[
    {
        id: 1,
        nome: "Estoque Principal",
        descricao: "Estoque principal da empresa",
    },
    {
        id: 2,
        nome: "Estoque de Retirada",
        descricao: "Estoque de retirada de produtos para entrega",
    },
]
```

**Códigos de Resposta**

| Código | Descrição
|-|-|
| 200 | Dados retornados com sucesso

---

### Detalhar estoque

`GET` /api/v1/estoque/{id}

**Exemplo de corpo da resposta**

```js
{
    id: 1,
    nome: "Estoque Principal",
    descricao: "Estoque principal da empresa",
}
```

**Códigos de Resposta**

| Código | Descrição
|-|-|
| 200 | Dados retornados com sucesso
| 404 | Não existe estoque com o id informado

---

### Cadastrar Estoque

`POST` /api/v1/estoque

**Campos da requisição**

| Campo     | Tipo   | Obrigatório?| Descrição
|-----------|--------|:-----------:|-|
|nome       |String  |sim          |Texto com o nome do estoque com no máximo 255 caracteres.
|descricao  |String  |não          |Texto com a descrição do estoque com no máximo 255 caracteres.

**Exemplo de corpo da requisição**

```js
{
    nome: "Estoque Principal",
    descricao: "Estoque principal da empresa",
}
```

**Códigos de Resposta**

| Código | Descrição
|-|-|
| 201 | Dados cadastrados com sucesso
| 400 | Erro de validação dos dados

---

### Apagar estoque

`DELETE` /api/v1/estoque/{id}

**Códigos de Resposta**

| Código | Descrição
|-|-|
| 204 | Dados apagados com sucesso
| 404 | Não existe estoque com o id informado

---

### Atualizar estoque

`PUT` /api/v1/estoque/{id}

**Campos da requisição**

| Campo     | Tipo   | Obrigatório?| Descrição
|-----------|--------|:-----------:|-|
|nome       |String  |sim          |Texto com o nome do estoque com no máximo 255 caracteres.
|descricao  |String  |não          |Texto com a descrição do estoque com no máximo 255 caracteres.

**Exemplo de corpo da requisição**

```js
{
    nome: "Estoque Principal",
    descricao: "Estoque principal da empresa",
}
```

**Códigos de Resposta**

| Código | Descrição
|-|-|
| 200 | Dados atualizados com sucesso
| 400 | Erro de validação dos dados
| 404 | Não existe estoque com o id informado

---

## :package: Produto
### Listar todas

`GET` /api/v1/produto

**Exemplo de corpo da resposta**

```js
[
    {
        id: 1,
        estoqueId: 1,
        nome: "Faca de Cozinha",
        descricao: "Facas de cozinha com Lâminas em Aço Inox e Cabos de Polipropileno Preto",
        imagemUrl: "https://controledeproduto.com.br/img/produtos/facadecozinha.png",
        quantidade: 100,
        quantidadeMinima: 10,
    },
    {
        id: 2,
        estoqueId: 1,
        nome: "Televisão",
        descricao: "Televisão 4K de 55 polegadas",
        imagemUrl: "https://controledeproduto.com.br/img/produtos/televisao.png",
        quantidade: 50,
        quantidadeMinima: 5,
    },
]
```

**Códigos de Resposta**

| Código | Descrição
|-|-
| 200 | Dados retornados com sucesso

---

### Detalhar produto

`GET` /api/v1/produto/{id}

**Exemplo de corpo da resposta**

```js
{
    estoque: {
       "id": 1
    },
    nome: "Faca de Cozinha",
    descricao: "Facas de cozinha com Lâminas em Aço Inox e Cabos de Polipropileno Preto",
    imagemUrl: "https://controledeproduto.com.br/img/produtos/facadecozinha.png",
    quantidade: 100,
    quantidadeMinima: 10,
}
```

**Códigos de Resposta**

| Código | Descrição
|-|-
| 200 | Dados retornados com sucesso
| 404 | Não existe produto com o id informado

---

### Estoque mínimo

`GET` /api/v1/produto/estoque-minimo

Essa endpoint vai servir como maneira facilitada de retornar uma lista dos produtos que possuem o parâmetro ``quantidade`` igual ao parâmetro ``quantidadeMinima``

**Exemplo de corpo da resposta**

```js
[
    {
        estoque: {
            "id": 1
        },
        nome: "Faca de Cozinha",
        descricao: "Facas de cozinha com Lâminas em Aço Inox e Cabos de Polipropileno Preto",
        imagemUrl: "https://controledeproduto.com.br/img/produtos/facadecozinha.png",
        quantidade: 10,
        quantidadeMinima: 10,
    }
]
```

**Códigos de Resposta**

| Código | Descrição
|-|-
| 200 | Dados retornados com sucesso

---

### Cadastrar Produto

`POST` /api/v1/produto

**Campos da requisição**

| Campo            | Tipo   | Obrigatório?| Descrição
|------------------|--------|:-----------:|-
|id (estoque)        |int     |sim          |O id do estoque que o produto pertence.
|nome              |String  |sim          |Texto com o nome do produto com no máximo 255 caracteres.
|descricao         |String  |sim          |Texto com a descrição do produto com no máximo 255 caracteres.
|quantidade        |int     |sim          |O valor da quantidade existente no produto.
|quantidadeMinima |int     |não          |O valor mínimo para alertar quantidade baixa no produto.
|imagemUrl        |String  |não          |Texto com a URL da imagem do produto.

**Exemplo de corpo de requisição**

```js
{
    estoque: {
       "id": 1
    },
    nome: "Faca de Cozinha",
    descricao: "Facas de cozinha com Lâminas em Aço Inox e Cabos de Polipropileno Preto",
    imagemUrl: "https://controledeproduto.com.br/img/produtos/facadecozinha.png".
    quantidade: 100,
    quantidadeMinima: 10,
}
```

**Códigos de Resposta**

| Código | Descrição
|-|-
| 201 | Produto cadastrado com sucesso
| 400 | Os campos enviados são inválidos

---

### Atualizar Produto

`PUT` /api/v1/produto/{id}

**Campos da requisição**

| Campo            | Tipo   | Obrigatório?| Descrição
|------------------|--------|:-----------:|-
|estoqueId        |int     |não          |O id do estoque que o produto pertence.
|nome              |String  |não          |Texto com o nome do produto com no máximo 255 caracteres.
|descricao         |String  |não          |Texto com a descrição do produto com no máximo 255 caracteres.
|quantidade        |int     |não          |O valor da quantidade existente no produto.
|quantidadeMinima |int     |não          |O valor mínimo para alertar quantidade baixa no produto.
|imagemUrl        |String  |não          |Texto com a URL da imagem do produto.

**Exemplo de corpo de requisição**

```js
{
    estoque: {
       "id": 1
    },
    nome: "Faca de Cozinha",
    descricao: "Facas de cozinha com Lâminas em Aço Inox e Cabos de Polipropileno Preto",
    imagemUrl: "https://controledeproduto.com.br/img/produtos/facadecozinha.png",
    quantidade: 100,
    quantidadeMinima: 10,
}
```

**Códigos de Resposta**

| Código | Descrição
|-|-
| 200 | Produto atualizado com sucesso
| 400 | Os campos enviados são inválidos
| 404 | Não existe produto com o id informado

---

### Apagar produto

`DELETE` /api/v1/produto/{id}

**Códigos de Resposta**

| Código | Descrição
|-|-
| 204 | Produto removido com sucesso
| 404 | Não existe produto com o id informado

---
