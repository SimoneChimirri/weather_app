# Weather API

Un'applicazione Spring Boot per ottenere dati meteorologici utilizzando l'API di OpenMeteo.

## Caratteristiche

- **API REST** per ottenere dati meteorologici
- **Caching** con Caffeine per migliorare le performance
- **Dockerizzazione** con Docker Compose
- **Validazione** dei parametri di input
- **Logging** strutturato
- **Health checks** per monitoraggio
- **CORS** configurato per le chiamate da frontend

## Tecnologie utilizzate

- Java 17
- Spring Boot 3.2.0
- Spring WebFlux (per le chiamate HTTP)
- Spring Cache (con Caffeine)
- Docker & Docker Compose
- Maven
- OpenMeteo API

## Struttura del progetto

```
src/
├── main/
│   ├── java/com/example/weather/
│   │   ├── config/
│   │   │   └── WeatherConfig.java
│   │   ├── controller/
│   │   │   └── WeatherController.java
│   │   ├── dto/
│   │   │   ├── WeatherDto.java
│   │   │   └── WeatherResponse.java
│   │   ├── service/
│   │   │   └── WeatherService.java
│   │   ├── util/
│   │   │   └── WeatherCodeMapper.java
│   │   └── WeatherApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/weather/
        └── WeatherAppApplicationTests.java
```

## Endpoint API

### Ottenere il meteo per coordinate

```http
GET /api/weather/coordinates?latitude=45.4642&longitude=9.1900
```

### Ottenere il meteo per città

```http
GET /api/weather/city/milano
```

### Ottenere solo il meteo attuale

```http
GET /api/weather/current?latitude=45.4642&longitude=9.1900
```

### Ottenere le previsioni orarie

```http
GET /api/weather/forecast/hourly?latitude=45.4642&longitude=9.1900
```

### Ottenere le previsioni giornaliere

```http
GET /api/weather/forecast/daily?latitude=45.4642&longitude=9.1900
```

### Health check

```http
GET /api/weather/health
```

## Città supportate

L'applicazione supporta le seguenti città italiane:
- Milano/Milan
- Roma/Rome
- Napoli/Naples
- Torino/Turin
- Firenze/Florence
- Bologna
- Venezia/Venice
- Genova/Genoa
- Palermo
- Bari

## Installazione e avvio

### Prerequisiti

- Java 17 o superiore
- Maven 3.6 o superiore
- Docker e Docker Compose

### Avvio con Docker Compose

1. Clona il repository:
```bash
git clone <repository-url>
cd weather-api
```

2. Compila l'applicazione:
```bash
mvn clean package -DskipTests
```

3. Avvia con Docker Compose:
```bash
docker-compose up -d
```

L'applicazione sarà disponibile su `http://localhost:8080`

### Avvio locale

1. Installa le dipendenze:
```bash
mvn clean install
```

2. Avvia l'applicazione:
```bash
mvn spring-boot:run
```

## Configurazione

Le principali configurazioni sono in `application.properties`:

```properties
# Server configuration
server.port=8080
server.servlet.context-path=/api

# OpenMeteo API configuration
openmeteo.api.url=https://api.openmeteo.com/v1/forecast
openmeteo.api.timeout=10s

# Cache configuration
spring.cache.type=caffeine
spring.cache.caffeine.spec=maximumSize=500,expireAfterWrite=10m
```

## Esempi di utilizzo

### Ottenere il meteo per Milano

```bash
curl "http://localhost:8080/api/weather/city/milano"
```

### Ottenere il meteo per coordinate specifiche

```bash
curl "http://localhost:8080/api/weather/coordinates?latitude=45.4642&longitude=9.1900"
```

### Risposta di esempio

```json
{
  "location": "Milano",
  "latitude": 45.4642,
  "longitude": 9.1900,
  "timezone": "Europe/Rome",
  "current": {
    "temperature": 15.2,
    "windSpeed": 5.4,
    "windDirection": 180,
    "weatherCode": 1,
    "weatherDescription": "Principalmente sereno",
    "time": "2024-01-15T14:00"
  },
  "hourlyForecast": [
    {
      "time": "2024-01-15T14:00",
      "temperature": 15.2,
      "humidity": 65,
      "precipitationProbability": 10,
      "windSpeed": 5.4
    }
  ],
  "dailyForecast": [
    {
      "date": "2024-01-15",
      "temperatureMax": 18.5,
      "temperatureMin": 8.2,
      "precipitationProbability": 20
    }
  ]
}
```

## Monitoraggio

L'applicazione espone endpoint di monitoraggio via Spring Actuator:

- `/actuator/health` - Status dell'applicazione
- `/actuator/metrics` - Metriche dell'applicazione
- `/actuator/info` - Informazioni sull'applicazione

## Caching

L'applicazione utilizza Caffeine per il caching dei dati meteorologici:
- Cache size: 500 elementi
- TTL: 10 minuti
- Chiave cache: coordinate o nome città

## Gestione errori

L'applicazione gestisce vari tipi di errori:
- Parametri di input non validi (400 Bad Request)
- Città non trovata (404 Not Found)
- Errori di connessione API (500 Internal Server Error)
- Timeout delle richieste (500 Internal Server Error)

## Contribuire

1. Fork del repository
2. Crea un branch per la tua feature
3. Commit delle modifiche
4. Push al branch
5. Crea una Pull Request

## Licenza

Questo progetto è rilasciato sotto licenza MIT.
