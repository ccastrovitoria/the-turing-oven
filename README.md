# The Turing Oven 🍕

Backend de uma pizzaria desenvolvido como projeto final da disciplina de **Fundamentos de Desenvolvimento de Software**.

## Sobre o projeto

O **The Turing Oven** é um sistema backend voltado à gestão de uma pizzaria, contemplando funcionalidades relacionadas a cardápios, pedidos de clientes, controle de estoque, pagamentos, preparação na cozinha e entregas.

O projeto segue princípios da **Arquitetura Limpa (Clean Architecture)**, separando regras de negócio, casos de uso e componentes responsáveis pela comunicação com recursos externos.

## Tecnologias

- Java 21
- Spring Boot 3.5.4
- Maven (Wrapper 3.9.16)
- Banco de dados H2
- JDBC (implementação inicial de persistência)
- JPA (migração prevista no projeto)
- JUnit / Spring Boot Test

## Estrutura do projeto

| Caminho | Finalidade |
|---|---|
| `src/main/java` | Código-fonte da aplicação |
| `src/main/resources` | Configurações e scripts do banco de dados |
| `src/test/java` | Testes automatizados |
| `out/docs/diagrams` | Diagramas de arquitetura e modelagem |
| `.mvn/wrapper` | Configuração do Maven Wrapper |
| `pom.xml` | Configuração do projeto Maven e suas dependências |

## Arquitetura do projeto

O The Turing Oven utiliza **Arquitetura Limpa (Clean Architecture)**, seguindo a organização apresentada no estudo de caso do professor. O objetivo é separar as responsabilidades da aplicação, evitando que as regras de negócio dependam diretamente das tecnologias utilizadas para executá-las.

### Camadas da aplicação

| Pasta | Responsabilidade |
|---|---|
| `Aplicacao/` | Contém os casos de uso que coordenam as funcionalidades do sistema, como listar cardápios e submeter pedidos. |
| `Dominio/` | Contém as entidades, regras de negócio, serviços de domínio e interfaces que representam as necessidades da pizzaria. |
| `Adaptadores/` | Contém os componentes que conectam a aplicação a recursos externos, como requisições HTTP e banco de dados. |

### Conceitos fundamentais da arquitetura

- **Caso de uso (UC):** representa uma funcionalidade do sistema e coordena as operações necessárias para executá-la.
- **Serviço de domínio (Service):** classe que disponibiliza operações relacionadas às regras e aos conceitos do negócio.
- **Repositório (Repository):** abstração utilizada para consultar ou modificar dados. A interface define as operações disponíveis, enquanto uma implementação concreta determina como elas são executadas.
- **Dependência:** objeto de que uma classe precisa para realizar suas responsabilidades.
- **Injeção de dependência:** técnica pela qual uma classe recebe os objetos de que precisa, em vez de criá-los diretamente.

De forma geral, o fluxo de uma funcionalidade que acessa o banco de dados é:

**Controller → Caso de uso → Serviço de domínio → Interface do repositório → Implementação de persistência → Banco de dados**

Cada componente desempenha uma responsabilidade específica, facilitando a compreensão, os testes e a manutenção do sistema.

### Interfaces e implementações

Um princípio importante da Arquitetura Limpa é que **o domínio define as operações de que precisa, mas não precisa conhecer a tecnologia utilizada para executá-las**.

Na **UC1 — Listar cardápios disponíveis**, por exemplo:

- `CardapioService` disponibiliza operações relacionadas aos cardápios e solicita a listagem ao repositório.
- `CardapioRepository` é a interface do domínio que declara as operações de acesso aos dados dos cardápios.
- `CardapioRepositoryJDBC` é o adaptador que implementa essas operações por meio de JDBC e comandos SQL.

O `CardapioService` utiliza a interface `CardapioRepository`, sem depender diretamente da classe `CardapioRepositoryJDBC`.

Essa separação permite substituir a implementação JDBC por outra baseada em JPA sem necessariamente alterar o serviço ou o caso de uso, desde que o contrato da interface seja preservado.

### Injeção de dependência no Spring

O Spring gerencia os componentes da aplicação e fornece os objetos necessários às classes, normalmente por meio de seus construtores.

As principais anotações utilizadas são:

- `@Component`: registra uma classe como componente gerenciado pelo Spring.
- `@Service`: identifica uma classe de serviço gerenciada pelo Spring.
- `@Autowired`: indica um ponto de injeção de dependência. Em classes com apenas um construtor, seu uso é opcional nas versões atuais do Spring.

Assim, uma classe pode utilizar outra sem precisar instanciá-la manualmente com `new`.

## Como executar

### Pré-requisitos

- JDK 21 ou versão compatível com a configuração do projeto
- Terminal disponível

Não é necessário instalar o Maven separadamente, pois o projeto inclui o **Maven Wrapper**.

### Iniciar a aplicação

**macOS / Linux**

```bash
./mvnw spring-boot:run
```

**Windows**

```cmd
mvnw.cmd spring-boot:run
```

Por padrão, a aplicação é iniciada em `http://localhost:8080`.

### Executar os testes

**macOS / Linux**

```bash
./mvnw clean test
```

**Windows**

```cmd
mvnw.cmd clean test
```

## Desenvolvimento

O projeto parte de um código inicial disponibilizado pelo professor. Os casos de uso e serviços adicionais serão desenvolvidos gradualmente, mantendo a separação de responsabilidades definida na arquitetura.

## Status do projeto

**Em desenvolvimento.**