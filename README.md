# 🎬 Movies with Cache

Projeto Spring Boot utilizando **WebFlux**, **Redis Cache** e **WebClient** para consumir APIs externas e melhorar performance através de cache.

---

## 🚀 Tecnologias

- Java 21  
- Spring Boot 3  
- Spring WebFlux  
- Redis Cache  
- WebClient  
- Reactor  
- JUnit 5 / Mockito  
- MockWebServer  

---

## 📂 Arquitetura

```
com.moraes.movies
├── client          # Clients HTTP (IMDb, etc)
├── configuration   # Configurações (Redis, WebClient)
├── controller      # Controllers REST
├── service         # Regras de negócio
├── DTO             # DTOs de resposta
```

---

## 🌐 Endpoints

### Buscar filmes
```
GET /movies
GET /movies?title=Batman
```

### Títulos dos filmes
```
GET /movies/titlesName
```

### Posters dos filmes
```
GET /movies/poster
```

Todos retornam **JSON Array**.

---

## ⚡ Cache

- Redis com TTL de **1 minuto**
- Serialização JSON
- Cache habilitado via `@EnableCaching`

---

## 🧪 Testes

Cobertura completa por camada:

| Camada | Tipo |
|------|------|
| Controller | WebFluxTest |
| Service | Unitário (Mockito) |
| Client | MockWebServer |
| Config | SpringBootTest |
| Cache | Mock Redis |

Rodar testes:
```bash
./gradlew test
```

---

## ▶️ Executar o projeto

```bash
./gradlew bootRun
```

Redis deve estar ativo:
```bash
docker run -p 6379:6379 redis
```

---

## 📦 Build

```bash
./gradlew clean build
```

---

## 👤 Autor

Vinicius Moraes  
GitHub: https://github.com/vmoraesinfo
