#!/bin/bash

# Script di build per Weather App

echo "🌤️  Weather App - Build Script"
echo "================================"

# Colori per output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Funzione per stampare messaggi colorati
print_status() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Verifica prerequisiti
check_prerequisites() {
    print_status "Verifica prerequisiti..."
    
    # Verifica Java
    if command -v java &> /dev/null; then
        JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d'.' -f1)
        if [ "$JAVA_VERSION" -ge 17 ]; then
            print_status "Java $JAVA_VERSION trovato ✓"
        else
            print_error "Java 17 o superiore richiesto. Versione trovata: $JAVA_VERSION"
            exit 1
        fi
    else
        print_error "Java non trovato. Installare Java 17 o superiore."
        exit 1
    fi
    
    # Verifica Maven
    if command -v mvn &> /dev/null; then
        MVN_VERSION=$(mvn -version | head -n1 | cut -d' ' -f3)
        print_status "Maven $MVN_VERSION trovato ✓"
    else
        print_error "Maven non trovato. Installare Maven 3.6 o superiore."
        exit 1
    fi
    
    # Verifica Docker (opzionale)
    if command -v docker &> /dev/null; then
        print_status "Docker trovato ✓"
        DOCKER_AVAILABLE=true
    else
        print_warning "Docker non trovato. Containerizzazione non disponibile."
        DOCKER_AVAILABLE=false
    fi
}

# Pulizia
clean_project() {
    print_status "Pulizia progetto..."
    mvn clean
    if [ $? -eq 0 ]; then
        print_status "Pulizia completata ✓"
    else
        print_error "Errore durante la pulizia"
        exit 1
    fi
}

# Compilazione
compile_project() {
    print_status "Compilazione progetto..."
    mvn compile
    if [ $? -eq 0 ]; then
        print_status "Compilazione completata ✓"
    else
        print_error "Errore durante la compilazione"
        exit 1
    fi
}

# Test
run_tests() {
    print_status "Esecuzione test..."
    mvn test
    if [ $? -eq 0 ]; then
        print_status "Test completati ✓"
    else
        print_warning "Alcuni test sono falliti"
    fi
}

# Package
package_project() {
    print_status "Creazione package..."
    mvn package -DskipTests
    if [ $? -eq 0 ]; then
        print_status "Package creato ✓"
        ls -la target/*.jar
    else
        print_error "Errore durante la creazione del package"
        exit 1
    fi
}

# Build Docker image
build_docker() {
    if [ "$DOCKER_AVAILABLE" = true ]; then
        print_status "Creazione immagine Docker..."
        docker build -t weather-app .
        if [ $? -eq 0 ]; then
            print_status "Immagine Docker creata ✓"
        else
            print_error "Errore durante la creazione dell'immagine Docker"
            exit 1
        fi
    else
        print_warning "Docker non disponibile. Saltando la creazione dell'immagine."
    fi
}

# Setup directory per dati
setup_data_directory() {
    print_status "Creazione directory dati..."
    mkdir -p data
    chmod 755 data
    print_status "Directory dati creata ✓"
}

# Funzione principale
main() {
    echo "Inizio build processo..."
    
    # Parsing argomenti
    SKIP_TESTS=false
    BUILD_DOCKER=false
    
    while [[ $# -gt 0 ]]; do
        case $1 in
            --skip-tests)
                SKIP_TESTS=true
                shift
                ;;
            --docker)
                BUILD_DOCKER=true
                shift
                ;;
            --help|-h)
                show_help
                exit 0
                ;;
            *)
                print_error "Opzione sconosciuta: $1"
                show_help
                exit 1
                ;;
        esac
    done
    
    # Esecuzione step
    check_prerequisites
    setup_data_directory
    clean_project
    compile_project
    
    if [ "$SKIP_TESTS" = false ]; then
        run_tests
    else
        print_warning "Test saltati (--skip-tests)"
    fi
    
    package_project
    
    if [ "$BUILD_DOCKER" = true ]; then
        build_docker
    fi
    
    print_status "Build completato con successo! 🎉"
    echo ""
    echo "Per avviare l'applicazione:"
    echo "  java -jar target/weather-app-*.jar"
    echo ""
    if [ "$DOCKER_AVAILABLE" = true ] && [ "$BUILD_DOCKER" = true ]; then
        echo "Oppure con Docker:"
        echo "  docker run -p 8080:8080 -v \$(pwd)/data:/app/data weather-app"
        echo ""
        echo "Oppure con Docker Compose:"
        echo "  docker-compose up -d"
        echo ""
    fi
    echo "Accedi all'app: http://localhost:8080"
}

# Funzione di help
show_help() {
    echo "Usage: $0 [OPTIONS]"
    echo ""
    echo "Opzioni:"
    echo "  --skip-tests    Salta l'esecuzione dei test"
    echo "  --docker        Crea anche l'immagine Docker"
    echo "  --help, -h      Mostra questo messaggio di aiuto"
    echo ""
    echo "Esempi:"
    echo "  $0                    # Build completo con test"
    echo "  $0 --skip-tests       # Build senza test"
    echo "  $0 --docker           # Build con immagine Docker"
    echo "  $0 --skip-tests --docker  # Build veloce con Docker"
}

# Esecuzione script
main "$@"