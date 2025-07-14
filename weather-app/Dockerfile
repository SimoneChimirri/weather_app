FROM openjdk:21-jdk-alpine

WORKDIR /app

COPY target/weather-app-*.jar app.jar

RUN mkdir -p /app/data

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

