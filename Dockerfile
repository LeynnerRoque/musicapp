# Estágio 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /project
COPY . .
# O Spring Boot gera o JAR em /target/nome-da-app.jar
RUN mvn clean package -DskipTests

# Estágio 2: Runtime
FROM eclipse-temurin:21-jre
ENV LANGUAGE='en_US:en'
WORKDIR /deployments

# Copiamos apenas o JAR final do Spring Boot
# Usamos o wildcard *.jar para não depender da versão do projeto (ex: 0.0.1-SNAPSHOT)
COPY --from=build /project/target/*.jar /deployments/app.jar

EXPOSE 8080

# No Render, é bom definir o Profile via variável de ambiente se necessário
# ENTRYPOINT padrão para Spring Boot
ENTRYPOINT ["java", "-jar", "/deployments/app.jar"]