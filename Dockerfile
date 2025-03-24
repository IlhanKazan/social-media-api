# Build aşaması
FROM maven:3.9.6-eclipse-temurin-17 as builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Bağımlılıkları indir ve uygulamayı build et
RUN mvn clean package -DskipTests

# Runtime aşaması (daha hafif)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/social-media-api-*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]