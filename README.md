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