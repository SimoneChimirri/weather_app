# weather_app
esercizio java - container

Un'applicazione web Java che visualizza i dati meteo da OpenMeteo API con grafici interattivi.

## Caratteristiche

- **Backend Spring Boot** con API REST
- **Database H2** per memorizzare le città
- **Frontend responsive** con Bootstrap e Chart.js
- **Grafici interattivi** per temperatura, umidità e velocità del vento
- **Containerizzazione Docker** completa
- **Gestione città** (aggiungi, elimina, visualizza)

## Tecnologie utilizzate

- **Java 17** - Linguaggio di programmazione
- **Spring Boot 3.2.0** - Framework backend
- **Spring Data JPA** - ORM per database
- **H2 Database** - Database in-memory/file
- **Thymeleaf** - Template engine
- **Bootstrap 5** - Framework CSS
- **Chart.js** - Libreria per grafici
- **Docker** - Containerizzazione
- **Maven** - Build tool

## Struttura del progetto

```
weather-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/weatherapp/
│       │       ├── WeatherApplication.java
│       │       ├── controller/
│       │       │   └── WeatherController.java
│       │       ├── entity/
│       │       │   └── City.java
│       │       ├── repository/
│       │       │   └── CityRepository.java
│       │       ├── service/
│       │       │   ├── WeatherService.java
│       │       │   └── CityService.java
│       │       ├── dto/
│       │       │   └── WeatherData.java
│       │       └── config/
│       │           └── DataInitializer.java
│       └── resources/
│           ├── application.properties
│           └── templates/
│               ├── index.html
│               └── weather.html
├── pom.xml
├── Dockerfile
├── docker-compose.yml
└── README.md
```

## Installazione e Avvio

### Prerequisiti

- Java 17 o superiore
- Maven 3.6 o superiore
- Docker e Docker Compose (per containerizzazione)

### Metodo 1: Avvio locale

1. **Clona il repository**:
   ```bash
   git clone <repository-url>
   cd weather-app
   ```

2. **Compila il progetto**:
   ```bash
   mvn clean compile
   ```

3. **Avvia l'applicazione**:
   ```bash
   mvn spring-boot:run
   ```

4. **Accedi all'app**:
   - Apri il browser su `http://localhost:8080`
   - Console H2: `http://localhost:8080/h2-console`

### Metodo 2: Docker Compose (Raccomandato)

1. **Compila il JAR**:
   ```bash
   mvn clean package -DskipTests
   ```

2. **Avvia con Docker Compose**:
   ```bash
   docker-compose up -d
   ```

3. **Accedi all'app**:
   - Applicazione: `http://localhost:8080`

4. **Ferma i container**:
   ```bash
   docker-compose down
   ```

### Metodo 3: Solo Docker

1. **Compila il JAR**:
   ```bash
   mvn clean package -DskipTests
   ```

2. **Costruisci l'immagine**:
   ```bash
   docker build -t weather-app .
   ```

3. **Avvia il container**:
   ```bash
   docker run -p 8080:8080 -v $(pwd)/data:/app/data weather-app
   ```

## Utilizzo

### Pagina Principale

- Visualizza tutte le città disponibili
- Aggiungi nuove città inserendo nome, nazione, latitudine e longitudine
- Elimina città esistenti
- Clicca su una città per vedere i dati meteo

### Pagina Meteo

- Visualizza dati meteo attuali (temperatura, umidità, vento)
- Grafici interattivi per le previsioni 24h:
  - Temperatura nel tempo
  - Umidità nel tempo
  - Velocità del vento
- Aggiornamento automatico ogni 5 minuti

### API Endpoints

- `GET /` - Pagina principale
- `GET /weather/{cityId}` - Pagina meteo per una città
- `GET /api/cities` - Lista delle città (JSON)
- `POST /api/cities` - Aggiungi nuova città
- `DELETE /api/cities/{id}` - Elimina città
- `GET /api/weather/{cityId}` - Dati meteo per una città (JSON)

## Configurazione

### Database H2

Il database H2 è configurato per salvare i dati in un file locale:

```properties
spring.datasource.url=jdbc:h2:file:./data/weather-db
spring.datasource.username=sa
spring.datasource.password=password
```

### OpenMeteo API

L'app utilizza l'API gratuita di OpenMeteo:
- URL: `https://api.open-meteo.com/v1/forecast`
- Nessuna chiave API richiesta
- Dati disponibili: temperatura, umidità, velocità e direzione del vento

## Città Pre-configurate

L'applicazione include queste città di default:

**Italia:**
- Roma, Milano, Napoli, Torino, Firenze, Venezia, Bologna, Genova, Palermo, Bari

**Europa:**
- London, Paris, Berlin, Madrid, Amsterdam

## Sviluppo

### Aggiungere nuove funzionalità

1. **Nuovi parametri meteo**: Modifica `WeatherData.java` e aggiorna i grafici
2. **Nuovi grafici**: Aggiungi canvas in `weather.html` e logica JavaScript
3. **Nuove città**: Usa il form nella pagina principale o l'API REST

### Testing

```bash
# Test unitari
mvn test

# Test con profilo specifico
mvn test -Dspring.profiles.active=test
```

### Logs

I log sono configurati per mostrare:
- Livello INFO per l'applicazione
- Livello INFO per Spring Web

## Troubleshooting

### Errori comuni

1. **Porta 8080 occupata**: Cambia la porta in `application.properties`
2. **Errore connessione OpenMeteo**: Verifica la connessione internet
3. **Database locked**: Assicurati che non ci siano altre istanze in esecuzione

### Monitoraggio

- Health check: `http://localhost:8080/actuator/health`
- Database console: `http://localhost:8080/h2-console`

## Licenza

Questo progetto è rilasciato sotto licenza MIT.

## Contributi

I contributi sono benvenuti! Per contribuire:

1. Fork del repository
2. Crea un branch per la tua feature
3. Commit delle modifiche
4. Push del branch
5. Crea una Pull Request

## Supporto

Per supporto o domande, apri una issue nel repository.
