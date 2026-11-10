# The Turing Oven 🍕

A pizza delivery backend developed as the final project for the **Software Development Fundamentals** course.

## About the Project

The Turing Oven is a backend system designed to manage the operations of an online pizzeria, including menu management, customer orders, inventory validation, payments, kitchen processing, and deliveries.

The project follows **Clean Architecture** principles, separating business rules, application use cases, and external adapters.

## Technologies

- Java 21
- Spring Boot 3.5.4
- Maven (Wrapper 3.9.16)
- H2 Database
- JDBC (starter implementation)
- JPA (planned persistence migration)
- JUnit / Spring Boot Test

## Project Structure

- `src/main/java` — Application source code
- `src/main/resources` — Configuration and database scripts
- `src/test/java` — Automated tests
- `out/docs/diagrams` — Architecture and design diagrams
- `.mvn/wrapper` — Maven Wrapper configuration
- `pom.xml` — Maven project configuration and dependencies

## Arquitetura do Projeto

O The Turing Oven utiliza **Arquitetura Limpa (Clean Architecture)**, seguindo a estrutura apresentada no estudo de caso do professor. O objetivo é separar as responsabilidades da aplicação, evitando que as regras de negócio dependam diretamente de tecnologias externas.

### Camadas da aplicação

| Pasta | Responsabilidade |
|---|---|
| `Aplicacao/` | Contém os casos de uso que coordenam as funcionalidades do sistema, como listar cardápios e submeter pedidos. |
| `Dominio/` | Contém as entidades, regras de negócio, serviços e interfaces que representam as necessidades da pizzaria. |
| `Adaptadores/` | Contém as implementações responsáveis pela comunicação com recursos externos, como requisições HTTP e banco de dados. |

### Interfaces e implementações

Um princípio importante da Arquitetura Limpa é que **o domínio define as operações de que precisa, mas não precisa conhecer a tecnologia utilizada para executá-las**.

Na UC1 — Listar cardápios disponíveis, temos:

- `CardapioService`: serviço de domínio que solicita a listagem de cardápios.
- `CardapioRepository`: interface do domínio que define as operações disponíveis para acessar os dados dos cardápios.
- `CardapioRepositoryJDBC`: adaptador que implementa essa interface utilizando JDBC.

O `CardapioService` utiliza `CardapioRepository`, e não diretamente `CardapioRepositoryJDBC`.

Essa separação permite substituir a implementação JDBC por uma implementação JPA sem alterar necessariamente o serviço ou o caso de uso, desde que o contrato da interface seja preservado.

### Injeção de dependência

O Spring gerencia os componentes da aplicação e fornece as dependências necessárias por meio de seus construtores.

No projeto, utilizamos anotações como:

- `@Component`: registra uma classe como componente gerenciado pelo Spring.
- `@Service`: identifica uma classe de serviço gerenciada pelo Spring.
- `@Autowired`: indica onde uma dependência deve ser injetada. É opcional quando a classe possui apenas um construtor.

Esse mecanismo permite conectar os componentes sem que cada classe precise criar manualmente os objetos dos quais depende.

## Getting Started

### Requirements

- Java Development Kit (JDK) 21 or compatible
- An available terminal

Maven does not need to be installed separately when using the Maven Wrapper.

### Running the Application

**macOS / Linux**

```bash
./mvnw spring-boot:run
```

**Windows**

```cmd
mvnw.cmd spring-boot:run
```

By default, the application runs at `http://localhost:8080`.

### Running Tests

**macOS / Linux**

```bash
./mvnw clean test
```

**Windows**

```cmd
mvnw.cmd clean test
```

## Development

This project builds upon starter code provided by the course professor. Additional use cases and services will be implemented incrementally.

## Project Status

Under development.