FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Installa curl per i health check
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Copia i file di build
COPY target/weather-api-*.jar app.jar

# Espone la porta 8080
EXPOSE 8080

# Avvia l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]
