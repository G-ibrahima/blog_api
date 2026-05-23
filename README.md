# Blog API 🚀

![CI](https://github.com/G-ibrahima/blog_api/actions/workflows/ci.yml/badge.svg)

API REST complète pour gérer un blog avec authentification JWT.

## Technologies
- Java 17 + Spring Boot 3
- Spring Security + JWT
- MySQL + JPA + Flyway
- Docker + Docker Compose
- GitHub Actions CI/CD

## Lancer avec Docker

```bash
docker-compose up --build
```

L'API sera disponible sur http://localhost:8080

## Documentation Swagger
http://localhost:8080/swagger-ui.html

## Endpoints principaux

### Auth
- POST /api/auth/register
- POST /api/auth/login

### Posts
- GET /posts
- POST /posts
- PUT /posts/{id}
- DELETE /posts/{id} (ADMIN seulement)

### Users
- GET /users
- POST /users

### Comments
- GET /comment/post/{postId}
- POST /comment

### Tags
- GET /tags
- POST /tags
- POST /tags/{tagId}/posts/{postId}

## Monitoring
- GET /actuator/health
- GET /actuator/metrics