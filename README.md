# E-Commerce REST API

Backendowa aplikacja typu REST API zbudowana w oparciu o framework **Spring Boot**, służąca do zarządzania katalogiem produktów, ich producentami oraz dynamicznymi atrybutami.

---

## Instalacja i Konfiguracja

### Wymagania wstępne
- Java SDK 21 lub nowsza
- PostgreSQL (uruchomiony lokalnie lub w chmurze)
- Maven (zintegrowany z IDE lub zainstalowany samodzielnie)

### Kroki instalacji

**1. Sklonuj repozytorium:**
```bash
git clone https://github.com/MichuZabba/e_commerce.git
cd e_commerce
```

**2. Skonfiguruj bazę danych Postgre:**
Otwórz plik `src/main/resources/application.properties` i zaktualizuj dane połączenia:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/twoja_nazwa_bazy
spring.datasource.username=twoj_uzytkownik
spring.datasource.password=twoje_haslo
spring.jpa.hibernate.ddl-auto=update
```

**3. Uruchom aplikację:**
Uruchom główną klasę `JavaTaskApplication` z poziomu IDE lub użyj terminala:
```bash
mvn spring-boot:run
```

---

## Testowanie API (Postman)

W głównym katalogu projektu znajduje się plik JSON z gotową kolekcją zapytań, która ułatwia testowanie wszystkich funkcjonalności.

**Jak zaimportować kolekcję:**
1. Otwórz Postmana
2. Kliknij **Import** w lewym górnym rogu
3. Przeciągnij i upuść plik `apiCollectionForPostman.postman_collection` z folderu projektu

> Kolekcja zawiera przykładowe Body zapytań (JSON) dla operacji POST i PUT, dzięki czemu nie musisz ich ręcznie wpisywać.

---

## Architektura i Technologie

- **Spring Boot 3** — podstawa aplikacji
- **Spring Data JPA** — komunikacja z bazą danych
- **JPA Specifications** — zaawansowane, dynamiczne filtrowanie produktów
- **MapStruct** — automatyczne mapowanie między encjami, DTO i obiektami odpowiedzi
- **Lombok** — redukcja kodu boilerplate (gettery, settery)
- **Liquibase** — zarządzanie migracjami schematu bazy danych
- **PostgreSQL** — relacyjna baza danych

---

## Architektura Warstw

Aplikacja opiera się na trójwarstwowej architekturze z wyraźnym podziałem odpowiedzialności:

- **DTO (Data Transfer Object)** — reprezentuje dane wejściowe przesyłane przez klienta do API (np. przy tworzeniu lub aktualizacji zasobu)
- **Response** — dedykowane klasy odpowiedzi dla każdego endpointu, reprezentujące dane zwracane do frontendu
- **Encja (Model)** — wewnętrzna reprezentacja danych w bazie danych, nieujawniana bezpośrednio na zewnątrz
- **AppMapper** — jeden centralny mapper (MapStruct) odpowiedzialny za konwersję między wszystkimi warstwami

---

## Kluczowe Funkcjonalności

### Dynamiczne Atrybuty
Każdy produkt może posiadać dowolną liczbę cech w formacie klucz-wartość, takich jak kolor, rozmiar czy waga.

### Dedykowane Response na każdy endpoint
Każdy endpoint API zwraca dedykowaną klasę odpowiedzi, co zapewnia czytelny kontrakt między backendem a frontendem.

---

## Struktura Endpointów

### Producers `/api/producers`
| Metoda | Endpoint | Opis |
|--------|----------|------|
| GET | `/api/producers` | Pobierz wszystkich producentów |
| GET | `/api/producers/{id}` | Pobierz producenta po ID |
| POST | `/api/producers` | Utwórz nowego producenta |
| PUT | `/api/producers/{id}` | Zaktualizuj producenta |
| DELETE | `/api/producers/{id}` | Usuń producenta |

### Products `/api/products`
| Metoda | Endpoint | Opis |
|--------|----------|------|
| GET | `/api/products` | Pobierz wszystkie produkty |
| GET | `/api/products/{id}` | Pobierz produkt po ID |
| GET | `/api/products/withAttribute/{attribute}` | Pobierz produkty z danym atrybutem |
| POST | `/api/products` | Utwórz nowy produkt |
| POST | `/api/products/commands/search` | Wyszukuje produkty z filtrowaniem i stronicowaniem |
| PUT | `/api/products/{id}` | Zaktualizuj produkt |
| DELETE | `/api/products/{id}` | Usuń produkt |
