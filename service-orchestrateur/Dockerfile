# 1. Image de base (Java 17)
FROM eclipse-temurin:17-jdk-alpine

# 2. Variable pour le fichier JAR
ARG JAR_FILE=target/*.jar

# 3. Copie du JAR dans le conteneur
COPY ${JAR_FILE} app.jar

# 4. Commande de lancement
ENTRYPOINT ["java","-jar","/app.jar"]