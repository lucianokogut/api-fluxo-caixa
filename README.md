# Repositório da API do Fluxo de Caixa para PS da ACT

<h1 align="center">
     ⚙️ <a href="#" alt="API do Fluxo de Caixa"> API do Fluxo de Caixa para Processo Seletivo </a> ⚙️
</h1>

<h3 align="center">
    🛠️ Esta API permite o controle de títulos a pagar ou a receber, controle de usuários vinculados aos lançamentos e relacionados com cada usuário autenticado. 🛠️ 
</h3>

<h4 align="left">
    🛠️ Esta é a primeira "peça" a ser executada para que o restante do projeto funcione de modo adequado... 🛠️
</h4>

<h4 align="center">
	🚧🧱 Concluído 🆗🚧🧱
</h4>

# Tabela de conteúdos

<!--ts-->

- [Sobre o projeto](#💻-sobre-o-projeto)
- [Estrutura do projeto](#🚏-estrutura-do-projeto)
- [Planejamento e desenvolvimento](#🛤️-planejamento-e-desenvolvimento)
- [Funcionalidades](#🎯-funcionalidades)
- [Layout da aplicação](#🎟️-layout-da-aplicação)
  - [Web](#web)
  - [API](#api)
- [Como executar o projeto](#🚀-como-executar-o-projeto)
  - [Pré-requisitos](#pré-requisitos)
  - [Rodando o Backend (API)](#🎲-rodando-o-backend-api)
  - [Rodando a aplicação web (Frontend)](#🧭-rodando-a-aplicação-web-frontend)
- [Tecnologias](#🥁-tecnologias)
  - [API](#api-fluxo-caixa-repositório)
  - [WebSite](#website-repositório)
- [Autor](#🦸-autor)
- [Licença](#📝-licença)
<!--te-->

## 💻 Sobre o projeto

Pela demanda encaminhada para desenvolvimento de uma aplicação, foi informado que um comerciante precisa controlar o seu fluxo de caixa diário com os lançamentos (débitos e créditos), também precisa de um relatório que disponibilize o saldo diário consolidado.

Como requisitos de negócio foram solicitados no mínimo dois serviços, um que permita o controle dos lançamentos e outro que possibilite a consolidação diária ou por período.

Para os requisitos técnicos foram solicitados:

- [x] desenho da solução
- [x] uso da linguagem de programação mais habitual
- [x] uso de boas práticas como Design Patterns, Padrões de Arquitetura, SOLID, etc
- [x] produção do README com instruções de como subir a aplicação local ou container e para utilização dos serviços
- [x] hospedar em repositório público do GitHub

♻️ API-FLUXO-CAIXA - é uma das peças essenciais para que ferramenta de controle, lançamentos e acompanhamento das contas de uma empresa e/ou pessoa física funcionem. Os comerciantes que desejam manter as informações sobre despesas e receitas mensais agrupadas por lojas e/ou unidades e/ou empresas e/ou setores ou diferentes centros de custos, podem usar este aplicação para consumo dos dados cadastrados.

A API do Fluxo de Caixa foi projetada e desenvolvida considerando o Padrão de Projeto Singleton, utilizando os princípios do SOLID e com a separação das classes em diferentes domínios de negócio para o isolamento de suas responsabilidades.

## 🚏 Estrutura do projeto

A distribuição das camadas planejadas para o projeto são:

- [x] common: camada responsável por manter as classes utilitárias que serão comuns dentro do projeto
- [x] config: camada responsável por armazenar as configurações do projeto
- [x] controller: camada responsável pelo controle dos endpoints que serão criados e atuarão como os acessos à API
- [x] domain: camada responsável pelos diferentes domínios de negócios com as definição das regras da API
  - Enum: local para manter os dados associados aos números utilizados nas listas pré-definidas
  - exception: local para manter os dados associados ao controle de exceções que podem ocorrer na API
  - model: local para manter a representação dos dados que manipulados e fornecer a comunicação entre as demais camadas do projeto (model layer X view layer X controller layer)
  - repository: local para manter os repositórios utilizados no projeto e na API
  - service: local para manter os serviços internos da API
- [x] dto: camada responsável pela transferência dos dados entre as demais camadas da aplicação, sem contemplar a lógica de negócios neste ambiente e sem envolver a camada de visão (view layer) e a camada de persistência dos dados (model layer)
- [x] handler: camada responsável pela definição das ações que os objetos e suas exceções terão com suas tratativas
- [x] security: camada responsável pela manutenção dos acessos seguros e as classes que necessitam de autenticação ou autorização na API

## 🛤️ Planejamento e desenvolvimento

O planejamento para o desenvolvimento do sistema considerou as seguintes rotinas:

- [x] desenvolvimento do cadastro (CRUD) de usuários
- [x] autenticação dos usuários via Token JWT
- [x] desenvolvimento do cadastro (CRUD) de centros de custos
- [x] vínculo de todo centro de custo a um único usuário
- [x] desenvolvimento dos lançamentos para débitos ou créditos (CRUD)
- [x] vínculo de todo título a um único usuário
- [x] vínculo de todo título a um ou mais centros de custo
- [x] desenvolvimento do serviço de fluxo de caixa
- [x] desenvolvimento do serviço de consolidação dos lançamentos
- [x] validação dos lançamentos de débitos e créditos, dos seus totais, do total consolidado filtrado por um período de data inicial e final

😰 Projeto desenvolvido durante os horários livres com base em alguns projetos das antigas que mantinha como referência da minha época de programação e aulas de Dev. Web.

---

## 🎯 Funcionalidades

- [x] Usuários ou empresas podem se cadastrar no ambiente via endpoint's específicos e posteriormente no ambiente web para:

  - [x] controlar lançamentos de títulos e/ou contas a pagar e a receber
  - [x] totalizar valores de saldo, contas a receber e a pagar por períodos escolhidos
  - [x] vincular lançamentos por unidade e/ou loja e/ou setor aqui chamados de centros de custos
  - [x] gerenciar valores de despesas e receitas por centros de custos
  - [x] acessar as informações de títulos lançados e seus centros de custos:
    - por usuários cadastrados, logados e que possuem restrições / inativação
    - por vínculo entre cada lançamento realizado com seu usuário específico
    - por data de vencimento entre um período inicial e final

---

## 🎟️ Layout da aplicação

O layout da aplicação está disponível pelo Draw.io:

<a href="https://www.figma.com/file/1SxgOMojOB2zYT0Mdk28lB/Ecoleta?node-id=136%3A546">
  <img alt="Made by tgmarinho" src="https://img.shields.io/badge/Acessar%20Layout%20-Figma-%2304D361">
</a>

### Web

<p align="center" style="display: flex; align-items: flex-start; justify-content: center;">
  <img alt="NextLevelWeek" title="#NextLevelWeek" src="./assets/web.svg" width="400px">

  <img alt="NextLevelWeek" title="#NextLevelWeek" src="./assets/sucesso-web.svg" width="400px">
</p>

### API

<p align="center">
  <img alt="NextLevelWeek" title="#NextLevelWeek" src="./assets/home-mobile.png" width="200px">

  <img alt="NextLevelWeek" title="#NextLevelWeek" src="./assets/detalhes-mobile.svg" width="200px">
</p>

---

## 🚀 Como executar o projeto

Este projeto é divido em duas "peças":

1. API atuando como Backend (diretório api-fluxo-caixa)
2. Ambiente Frontend atuando como manutenção dos lançamentos (diretório web-fluxo-caixa)

💡Tanto o Frontend quanto o Mobile precisam que o Backend esteja sendo executado para funcionar.

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html), [Spring Boot 3.0.0](https://spring.io/), [Maven Repository POM](https://maven.apache.org/pom.html).
Além disto é bom ter um editor para trabalhar com o código como [VSCode](https://code.visualstudio.com/)

#### 🎲 Rodando o Backend (API)

```bash

# Clone este repositório
$ git clone git@github.com:lucianokogut/api-fluxo-caixa.git

# Acesse o diretório do projeto pelo terminal/cmd
$ cd api-fluxo-caixa

```

# Extraia e adicione as informações básicas do modelo de dados via JSON do Insomnia

<p align="center">
  <a href="https://github.com/lucianokogut/api-fluxo-caixa/blob/main/Insomnia-api-fluxo-caixa-JSON" target="_blank"><img src="https://insomnia.rest/images/run.svg" alt="Execute pelo Insomnia"></a>
</p>

```bash

# Inicie o ambiente da JVM com o JDK do Java e a instalação das dependências via Maven
$ '%JAVA_HOME\bin\java.exe' 'com.actcarrefour.fluxocaixa.FluxocaixaApplication'

```

# O servidor iniciará na porta:8080 utilizando os recursos do Spring Boot 3.0

#### 🧭 Rodando a aplicação web (Frontend)

```bash

# Clone o repositório indicado abaixo
$ git clone git@github.com:lucianokogut/web-fluxo-caixa.git

# Acesse o diretório do projeto pelo terminal/cmd
$ cd web-fluxo-caixa

# Vá para o diretório da aplicação Frontend
$ cd www

# Execute a aplicação
$ '%JAVA_HOME\bin\java.exe' 'com.actcarrefour.fluxocaixa.WebfluxocaixaApplication'

# A aplicação ficará disponível na porta:9090 e acessível pelo navegador no endereço http://127.0.0.1:9090 ou http://localhost:9090

```

---

## 🥁 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

#### **API-FLUXO-CAIXA** [Repositório](https://github.com/lucianokogut/api-fluxo-caixa)

([Java](https://www.java.com/pt-BR/) + [Spring](https://spring.io/))

- **[PostgreSQL](https://www.postgresql.org/)**
- **[Hibernate](https://hibernate.org/)**
- **[Jakarta](https://jakarta.ee/)**
- **[JWT](https://jwt.io/)**
- **[Log4j](https://logging.apache.org/log4j/2.x/)**
- **[Tomcat via Spring](https://spring.io/)**

#### **Website** [Repositório](https://github.com/lucianokogut/)

([Angular](https://angular.io/) + [TypeScript](https://www.typescriptlang.org/))

- **[H2 Database](https://www.h2database.com/html/main.html)**

---

## 🦸 Autor

<a>
 <img style="border-radius: 50%;" src="https://media.licdn.com/dms/image/C4D03AQGbv4yhZGNDOQ/profile-displayphoto-shrink_400_400/0/1663678786858?e=1685577600&v=beta&t=XzyZTgGc4yy5bjrj4CteAdHSnt3gqMWIcIY8n41eAAY" width="100px;" alt=""/>
 <br />
 <sub><b>Luciano (José) Kogut</b></sub></a> 🚀</a>
 <br />

[![Linkedin Badge](https://img.shields.io/badge/-Luciano-Kogut-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/lucianokogut/)](https://www.linkedin.com/in/lucianokogut/)
[![Hotmail Badge](https://img.shields.io/badge/-ljkogut@hotmail.com-blue?style=flat-square&logo=Hotmail&logoColor=white&link=mailto:ljkogut@hotmail.com)](mailto:ljkogut@hotmail.com)

---

## 📝 Licença

Este projeto é exclusivo para uma etapa de processo seletivo.

Feito com 😰 nos tempos livre, entre as aulas e o trabalho de coordenador (que não é pouco...) 👋🏽 [Entre em contato!](https://www.linkedin.com/in/lucianokogut/)

---
.
