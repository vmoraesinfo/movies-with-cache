# Movies With Cache 🎬

A reactive Spring Boot application that consumes external movie APIs, caches responses using Redis, and exposes REST endpoints returning JSON arrays.

## 🚀 Technologies

- Java 21
- Spring Boot 3 (WebFlux)
- Spring Cache
- Redis
- WebClient
- JUnit 5 / Mockito
- Gradle

## 🏗 Architecture

- Controller layer (Reactive)
- Service layer (Business logic + Cache)
- Client layer (External APIs)
- Configuration layer (Redis, WebClient)

## 📌 Endpoints

### Get all movies or filter by title
GET /movies  
GET /movies?title=Inception

### Get all movie titles (JSON Array)
GET /movies/titlesName

### Get all movie posters (JSON Array)
GET /movies/poster

## 🧠 Cache Strategy

- Redis Cache Manager
- TTL: 1 minute
- JSON serialization with Jackson

## 🧪 Tests

- Unit tests for Services
- Controller tests using WebTestClient
- Configuration tests for Redis and WebClient
- Reactive tests with StepVerifier

## ▶️ Running the project

### Requirements
- Java 21
- Docker (for Redis)

### Run Redis
```bash
docker run -d -p 6379:6379 redis
```

### Run application
```bash
./gradlew bootRun
```

### Run tests
```bash
./gradlew test
```

## 📄 Author

Vinicius Moraes  
GitHub: https://github.com/vmoraesinfo
