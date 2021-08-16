# Up42 feature Service

This service is used to query features and image data. It exposes the following end-points.

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

## Tool version:

- JDK 11 or later
- Gradle 7.1
- Docker 20.x

### To set up and run the application as docker container:

```bash
docker-compose up -d
```

### To run the application as springboot jar:

```bash
./gradlew bootRun
```

### To run the junit tests:

```bash
./gradlew test
```
