#!/bin/bash

# Script per compilare e avviare l'applicazione Weather API

echo "🌤️  Building Weather API Application..."

# Controlla se Maven è installato
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven non trovato. Installa Maven per continuare."
    exit 1
fi

# Controlla se Docker è installato
if ! command -v docker &> /dev/null; then
    echo "❌ Docker non trovato. Installa Docker per continuare."
    exit 1
fi

# Controlla se Docker Compose è installato
if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose non trovato. Installa Docker Compose per continuare."
    exit 1
fi

# Pulizia e compilazione
echo "📦 Compilazione del progetto..."
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Compilazione completata con successo!"
else
    echo "❌ Errore durante la compilazione."
    exit 1
fi

# Avvio con Docker Compose
echo "🐳 Avvio dell'applicazione con Docker Compose..."
docker-compose up -d

if [ $? -eq 0 ]; then
    echo "✅ Applicazione avviata con successo!"
    echo "🌐 L'applicazione è disponibile su: http://localhost:8000"
    echo "🔍 Health check: http://localhost:8080/api/weather/health"
    echo "📖 Esempio API: http://localhost:8080/api/weather/city/milano"
else
    echo "❌ Errore durante l'avvio dell'applicazione."
    exit 1
fi

# Mostra i log
echo "📋 Visualizzazione dei log (premi Ctrl+C per uscire):"
docker-compose logs -f