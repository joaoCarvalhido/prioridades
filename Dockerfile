# ==========================
# Etapa 1: Build com Maven
# ==========================
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia os arquivos de configuração e código
COPY pom.xml .
COPY src ./src

# Compila o projeto, gerando o JAR
RUN mvn clean package -DskipTests

# ==========================
# Etapa 2: Imagem final leve
# ==========================
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR compilado da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta do Spring Boot
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]