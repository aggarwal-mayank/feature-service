# Up42 feature Service

## Tool version:

- JDK 11 or later
- Gradle 7.1
- Docker 20.x

### To run the application as docker container, run:

```bash
docker-compose up -d
```

### To run the application as jar

```bash
./gradlew bootRun
```

### To run tests

```bash
./gradlew test
```

## Exposed Endpoints

- Get all features: http://localhost:8080/features

```bash
curl -v http://localhost:8080/features
```

- Get a feature by id: http://localhost:8080/features/{id}

```bash
curl -v http://localhost:8080/features/cf5dbe37-ab95-4af1-97ad-2637aec4ddf0
```

- Get quick look of id : http://localhost:8080/features/{id}/quicklook

```bash
curl -v --output image.png http://localhost:8080/features/cf5dbe37-ab95-4af1-97ad-2637aec4ddf0/quicklook 
```
