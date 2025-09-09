# Etapa 1: Build
FROM gradle:8.11.1-jdk17 AS build
WORKDIR /app

# Copia arquivos de configuração do Gradle
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./

# Baixa dependências para cachear camadas
RUN ./gradlew dependencies --no-daemon || return 0

# Copia o código fonte
COPY src ./src

# Gera o JAR do projeto
RUN ./gradlew bootJar --no-daemon

# Etapa 2: Runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o JAR da etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
