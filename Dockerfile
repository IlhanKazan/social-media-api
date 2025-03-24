# Build aşaması
FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline  # Bağımlılıkları önbelleğe al

COPY src ./src
RUN mvn clean package -DskipTests && \
    ls -la /app/target/  # JAR'ın oluştuğunu kontrol

# Runtime aşaması
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/social-media-api-*.jar app.jar

# JAR içindeki ana sınıfı kontrol
RUN jar tf app.jar | grep .class  # Sınıfların yüklendiğini doğrula

ENTRYPOINT ["java", "-jar", "app.jar"]