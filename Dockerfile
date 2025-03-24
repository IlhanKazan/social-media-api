# Build aşaması
FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Runtime aşaması (hafif)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/social-media-api-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]