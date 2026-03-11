# E-Commerce REST API

Backendowa aplikacja typu REST API zbudowana w oparciu o framework **Spring Boot**, służąca do zarządzania katalogiem produktów, ich producentami oraz dynamicznymi atrybutami.

---

## Instalacja i Konfiguracja

### Wymagania wstępne

- Java SDK 17 lub nowsza
- PostgreSQL (uruchomiony lokalnie lub w chmurze)
- Maven (zintegrowany z IDE lub zainstalowany samodzielnie)

### Kroki instalacji

**1. Sklonuj repozytorium:**

```bash
git clone https://github.com/MichuZabba/e_commerce.git
cd e_commerce
```

**2. Skonfiguruj bazę danych:**

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
3. Przeciągnij i upuść plik `e_commerce.postman_collection.json` z folderu projektu

> Kolekcja zawiera przykładowe Body zapytań (JSON) dla operacji POST i PUT, dzięki czemu nie musisz ich ręcznie wpisywać.

---

## Architektura i Technologie

- **Spring Boot 3** — podstawa aplikacji
- **Spring Data JPA** — komunikacja z bazą danych
- **JPA Specifications** — zaawansowane, dynamiczne filtrowanie produktów
- **Lombok** — redukcja kodu boilerplate (gettery, settery)
- **Liquibase** — zarządzanie migracjami schematu bazy danych
- **PostgreSQL** — relacyjna baza danych

---

## Kluczowe Funkcjonalności

### Dynamiczne Atrybuty
Każdy produkt może posiadać dowolną liczbę cech w formacie klucz-wartość, takich jak kolor, rozmiar czy waga.

### Zaawansowane Wyszukiwanie
Możliwość filtrowania produktów po konkretnych kluczach atrybutów przy użyciu Criteria API (Specifications).
