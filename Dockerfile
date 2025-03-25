FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8082
WORKDIR /app
COPY target/social-media-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]