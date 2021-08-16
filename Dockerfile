FROM gradle:7.1-jdk11 AS gradle
WORKDIR /home/gradle
COPY . .
RUN gradle build --no-daemon

FROM openjdk:11.0.7-jdk-slim AS feature
WORKDIR /app
COPY --from=gradle /home/gradle/build/libs/app.jar .
ENTRYPOINT [ "java", "-jar", "app.jar" ]