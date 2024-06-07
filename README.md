# Beach Report

Todos os endpoints estão disponíveis em uma coleção para teste via Postman. Utilizando o arquivo endpoints-postman.json localizado na pasta raíz do projeto.

## Integrantes
### Angélica Ferreira de Matos Melo - RM550776 - Planejamento, Definição do escopo do projeto, Desenvolvimento, Homologação/Testes, Deploy/Go Live.
### Ricardo Yuri Gonçalves Domingues - RM551808 - Planejamento, Definição do escopo do projeto, Desenvolvimento.
### Eduardo Foncesca Finardi - RM98624 - Planejamento, Definição do escopo do projeto, Documentação.
### Matheus Roberto Aparecido de M.C.P de Souza - RM98581 - Planejamento, Definição do escopo do projeto.
### Lucca Rinaldi Valladão de Freitas - RM98207 - Planejamento, Definição do escopo do projeto.

## Como rodar o projeto
Primeiramente certifique-se que seu computador possui o Java 17 instalado, assim como a versão mais recente do Maven.

Clone este repositório na sua pasta de preferência. Utilizando um terminal, digite o seguinte comando:

    mvn spring-boot:run

Caso esteja usando uma IDE, abra o projeto na seu editor de preferência, localize a classe **BeachReportApplication** e execute o projeto através do método main localizado nesta classe.

Utilize o arquivo *endpoints-postman.json* localizado na pasta raíz do projeto para realizar testes locais das chamadas de API persistência de informações no banco de dados.

## Endpoints

### Usuário

GET /usuario

Lista todos os usuários cadastrados no sistema.

*Códigos de status*
- 200 sucesso

---
GET /usuario/{id}

Retorna os detalhes de um usuário com o 'id' informado.

*Códigos de status*
- 200 sucesso
- 404 id não encontrado

---
POST /usuario

Cadastrar um novo usuário.

| campo    | tipo   | obrigatório | descrição 
|----------|--------|------------|-----------
| cpf      | long   | sim        | um cpf para identificar a cadastro 
| senha    | string | sim        | senha escolhida pelo usuário 
| email    | string | sim        | e-mail de usuário
| cep      | long   | sim        | CEP de usuário
| ddd      | long   | sim        | DDD de usuário
| telefone | long   | sim        | telefone de usuário

```json
{
  "cpf":12345,
  "senha":"12345",
  "email":"email@email.com",
  "cep":12345,
  "ddd":11,
  "telefone":12345
}
```

*Códigos de status*
- 201 criado com sucesso
- 400 validação falhou

---
DELETE /usuario/{id}

Apaga o usuário com o 'id' informado.

*Códigos de status*
- 204 apagado com sucesso
- 404 id não encontrado

---
PUT /usuario/{id}

Altera o usuário com o 'id' informado.

| campo | tipo | obrigatório | descrição 
|-------|------|-------------|-----------
| senha    | string | sim        | senha escolhida pelo usuário 
| email    | string | sim        | e-mail de usuário
| cep      | long   | sim        | CEP de usuário
| ddd      | long   | sim        | DDD de usuário
| telefone | long   | sim        | telefone de usuário

*Códigos de status*
- 200 sucesso
- 404 id não encontrado
- 400 validação falhou
```json
{
  "senha":"NovaSenha",
  "email":"novo-email@email.com",
  "cep":54321,
  "ddd":54321,
  "telefone":54321
}
```

### Relato

GET /relato

Lista todas os relatos cadastrados no sistema.

*Códigos de status*
- 200 sucesso

---
GET /relato/{id}

Retorna os detalhes de um relato com o 'id' informado.

*Códigos de status*
- 200 sucesso
- 404 id não encontrado

---
POST /relato

Cadastrar um novo relato.

| campo           | tipo   | obrigatório | descrição 
|-----------------|--------|------------|-----------
| relato          | String | sim        | descrição de um relato 
| latitude        | double | sim        | número representativo de latitude 
| longitude       | double | sim        | número representativo de longitude
| praia_suja      | char   | sim        | "S" ou "N" para praia suja
| envolve_animais | char   | sim        | "S" ou "N" para praia suja
| id_usuario      | long   | sim        | id do usuário que realizou o relato

```json
{
  "relato":"Meu relato é esse",
  "latitude":1.0,
  "longitude":1.0,
  "praia_suja":"S",
  "envolve_animais":"N",
  "id_usuario":1
}
```

*Códigos de status*
- 201 criado com sucesso
- 400 validação falhou

---
DELETE /relato/{id}

Apaga o relato com o 'id' informado.

*Códigos de status*
- 204 apagado com sucesso
- 404 id não encontrado
---
PUT /relato/{id}

Altera o relato com o 'id' informado.

| campo | tipo | obrigatório | descrição 
|-------|------|-------------|-----------
| relato          | String | sim        | descrição de um relato 
| latitude        | double | sim        | número representativo de latitude 
| longitude       | double | sim        | número representativo de longitude

*Códigos de status*
- 200 sucesso
- 404 id não encontrado
- 400 validação falhou

```json
{
  "relato":"Meu Novo Relato atualizado",
  "latitude":2.0,
  "longitude":2.0
}
```
---
PATCH /relato/like/{id}

Da um like para o post referenciado.

*Códigos de status*
- 200 like dado com sucesso
- 404 id não encontrado
- 500 erro ao dar like
---

## Link do vídeo de apresentação na nossa Proposta tecnológica: 
https://youtu.be/TIfaJ7jkS3M?feature=shared




