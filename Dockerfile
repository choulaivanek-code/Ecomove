# =============================================
# ÉTAPE 1 : Build avec Maven
# =============================================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copier pom.xml en premier (cache des dépendances)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source et compiler
COPY src ./src
RUN mvn clean package -DskipTests -B

# =============================================
# ÉTAPE 2 : Image finale légère
# =============================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copier le JAR depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Port exposé
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]