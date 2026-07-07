# Notes API

A lightweight yet production-inspired REST API built with **Spring Boot** to understand the core concepts of backend development using the Spring ecosystem.

The project started as a simple CRUD application and was gradually enhanced with features commonly found in enterprise Spring Boot applications, including layered architecture, DTOs, validation, global exception handling, pagination, custom JPQL queries, Swagger documentation, Docker support, testing, and more.

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Swagger / OpenAPI
- Lombok
- JUnit 5
- Mockito
- Docker
- GitHub Actions

---

# Features

## Notes Management

- Create Notes
- Retrieve Notes
- Update Notes
- Delete Notes

---

## Search

- Search notes by title
- Keyword search across title and content
- Custom JPQL queries

---

## Pagination & Sorting

Supports pagination using Spring Data JPA.

Example:

```http
GET /api/v1/notes?page=0&size=5&sort=id,desc
```

---

## Request Validation

Uses Jakarta Bean Validation.

Examples:

- Required title
- Required content
- Maximum character limits

---

## Global Exception Handling

Validation errors are handled centrally using:

- `@ControllerAdvice`
- `@ExceptionHandler`

Example response:

```json
{
  "success": false,
  "message": "Validation failed",
  "errors": {
    "title": "Title is required",
    "content": "Content is required"
  }
}
```

---

## DTO Layer

The API does not expose entities directly.

```
Client
      ↓
NoteRequest DTO
      ↓
Service
      ↓
Entity
      ↓
Database
      ↓
Entity
      ↓
NoteResponse DTO
      ↓
Client
```

---

## Standardized API Responses

Successful responses are wrapped using a generic response model.

Example:

```json
{
  "success": true,
  "message": "Note created successfully",
  "data": {
    "id": 1,
    "title": "Spring Boot",
    "content": "Learning Spring Boot",
    "createdAt": "...",
    "updatedAt": "..."
  }
}
```

---

## Audit Fields

Each note automatically stores:

- createdAt
- updatedAt

using:

- `@PrePersist`
- `@PreUpdate`

---

## Logging

Business operations are logged using SLF4J.

Examples:

```
Creating note with title: Spring Boot
Note created successfully with id: 1

Updating note with id: 1

Deleting note with id: 1
```

---

## Health Endpoint

```
GET /health
```

Example response:

```json
{
  "status": "UP",
  "application": "Spring Notes API",
  "version": "1.0.0",
  "timestamp": "..."
}
```

---

## Swagger Documentation

Interactive API documentation available at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Docker Support

The application can be containerized and run using Docker.

Build:

```bash
docker build -t spring-notes-api .
```

Run:

```bash
docker run -p 8080:8080 spring-notes-api
```

---

## Testing

Includes automated tests using:

- JUnit 5
- Mockito
- Spring MockMvc

Tests cover:

- Service layer
- Controller validation

---

## GitHub Actions

Continuous Integration workflow automatically:

- Builds the project
- Runs unit tests
- Validates every push to the repository

---

# Project Structure

```
src
├── main
│   ├── java
│   │   └── com.pranay.notesapi
│   │       ├── config
│   │       ├── controller
│   │       ├── dto
│   │       ├── exception
│   │       ├── model
│   │       ├── repository
│   │       ├── service
│   │       └── NotesapiApplication.java
│   └── resources
│       ├── application.properties
│       └── application-dev.properties
│
└── test
    └── java
```

---

# Architecture

```
               HTTP Request
                     │
                     ▼
              REST Controller
                     │
                     ▼
                 Service Layer
                     │
                     ▼
              Repository Layer
                     │
                     ▼
            Spring Data JPA
                     │
                     ▼
                 Hibernate
                     │
                     ▼
                H2 Database
```

---

# Running Locally

Clone the repository:

```bash
git clone <repository-url>
```

Navigate into the project:

```bash
cd notesapi
```

Run the application:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

---

# Running Tests

```bash
./mvnw test
```

Windows:

```powershell
.\mvnw.cmd test
```

---

# Spring Concepts Covered

This project was built to understand the Spring ecosystem from the ground up.

Topics explored include:

- Spring Boot
- Dependency Injection
- Beans
- Layered Architecture
- REST APIs
- Spring MVC
- JPA & Hibernate
- JpaRepository
- Custom JPQL
- DTOs
- Validation
- Global Exception Handling
- Pagination
- Sorting
- Audit Fields
- Profiles
- Externalized Configuration
- Logging
- Swagger/OpenAPI
- Docker
- Unit Testing
- Controller Testing
- GitHub Actions

---

# Purpose

This project was intentionally kept compact while progressively incorporating production-inspired backend practices.

Rather than focusing on application complexity, the emphasis was on understanding **how Spring Boot applications are structured**, how requests flow through each layer, and how commonly used Spring features work together to build maintainable REST APIs.

---

## Author

**Pranay Mishra**

Mechanical Engineering Undergraduate at BIT Mesra

Passionate about Backend Development, Full Stack Development, and building scalable software systems.
