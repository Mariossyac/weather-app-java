# PROPUNERE FORMALĂ DE PROIECT UNIVERSITAR
# GeoWeather Pro — Sistem Integrat de Monitorizare, Prognoză și Analiză Meteorologică în Java

---

## Date de Identificare Academică

* **Instituție de Învățământ Superior:** Universitatea din București / Universitatea POLITEHNICA / UBB Cluj / Universitatea "Alexandru Ioan Cuza"
* **Facultate:** Facultatea de Matematică și Informatică / Facultatea de Automatică și Calculatoare / Facultatea de Informatică
* **Specializare & An de Studiu:** Informatică / Calculatoare și Tehnologia Informației (CTI) — Anul III, Semestrul I
* **Disciplina:** Programare Avansată în Java / Programare Orientată pe Obiecte (POO) / Inginerie Software
* **Tip Lucrare:** Proiect Semestrial Aplicativ de Sinteză
* **Student Proponent:** [Nume și Prenume Student], Grupa [Număr Grupă], E-mail: student@universitate.ro
* **Cadru Didactic Coordonator:** Prof. Dr. Ing. / Conf. Univ. Dr. [Nume Coordonator Curs/Laborator]
* **Data Înaintării Propunerii:** Semestrul I, An Universitar 2026–2027

---

# Cuprinsul Documentului

1. **Sinteză Executivă & Motivația Temei**
2. **Analiza Comparativă a celor Trei Variante Arhitecturale**
   * 2.1. Varianta 1: Aplicație Desktop GUI Pur Java (JavaFX / Swing cu FlatLaf)
   * 2.2. Varianta 2: Aplicație Web Client-Server (Spring Boot 3.x Backend + Frontend Web)
   * 2.3. Varianta 3: Sistem Distribuit / Orientat pe Date (Java Core Daemon + Raw JDBC + Scripting IPC)
   * 2.4. Tabel Comparativ Sintetic și Matrice Multicriterială
   * 2.5. Varianta Recomandată pentru Curs și Argumentarea Academică
3. **Integrarea cu API-uri Meteorologice Publice & Strategii de Fiabilitate**
   * 3.1. Furnizori de Date (OpenWeatherMap, Open-Meteo, WeatherAPI)
   * 3.2. Fluxul de Date End-to-End (Diagrame ASCII)
   * 3.3. Caching Inteligent în Memorie cu Politici de Evacuare și TTL
   * 3.4. Mod Offline / Serviciu Mock pentru Evaluare Independentă
4. **Matricea Exhaustivă de Corespondență a Conceptelor Academice Java**
   * 4.1. Tabel Sinoptic Curricular: Concepte Teoretice vs. Implementare Concretă
   * 4.2. Programare Orientată pe Obiecte (OOP)
   * 4.3. Corespondența Structurilor de Date și a Colecțiilor Java (Collections Framework)
   * 4.4. Procesare Declarativă cu Java Streams & Expresii Lambda
   * 4.5. Ierarhia Custom de Excepții și Managementul Resurselor
   * 4.6. Persistență Relațională (JDBC) și Operațiuni Moderne I/O (NIO.2)
   * 4.7. Fire de Execuție, Concurență și Asincronism Non-blocant
   * 4.8. Șabloane de Proiectare Software (Design Patterns GoF)
   * 4.9. Calitatea Codului, Testare Unitară (JUnit 5 & Mockito) și Documentare
5. **Descrierea Formală a Proiectului pentru Profesor (Fișa Oficială de Aprobare)**
   * 5.1. Titlu, Context și Obiective de Învățare
   * 5.2. Specificația Cerințelor Funcționale (Modelul MoSCoW)
   * 5.3. Specificația Cerințelor Non-Funcționale (Performanță, Robusteză, Concurrency, Securitate)
   * 5.4. Arhitectura Tehnică & Structura Ierarhică a Pachetelor (`ro.univ.weather.*`)
   * 5.5. Diagrame ASCII de Arhitectură și Diagrame de Clase Reprezentative
   * 5.6. Planificarea pe Etape a Dezvoltării (WBS pe 8 Săptămâni cu Livrabile Intermediare)
   * 5.7. Ghid de Instalare, Configurare și Rulare Rapidă pentru Evaluator
   * 5.8. Grila Academică de Evaluare și Notare (10 Puncte cu Defalcare Riguroasă)
   * 5.9. Formular de Aprobare și Semnătură Curs / Laborator
6. **Bibliografie și Resurse Tehnice de Referință**

---

# 1. Sinteză Executivă & Motivația Temei

### 1.1. Context și Relevanță Practică
Starea vremii și dinamica climatică reprezintă domenii cu impact critic asupra activității cotidiene, logisticii, agriculturii și managementului riscului. În prezent, furnizorii de date meteorologice oferă acces prin interfețe de programare a aplicațiilor (REST APIs) la cantități masive de telemetrie în timp real: temperaturi curente, presiune barometrică, curenți atmosferici, prognoze orare pe mai multe zile, alerte severe și indici de calitate a aerului.

Proiectarea unei aplicații meteorologice complete — denumită **GeoWeather Pro** — reprezintă o punte ideală între rigorile teoretice ale științei calculatoarelor și cerințele industriei moderne de software. Aplicația propusă depășește stadiul unui simplu vizualizator de date („weather widget”), oferind un sistem complet dotat cu mecanisme de persistență relațională, interogare asincronă, caching de performanță, monitorizare automată în fundal și calcul statistic avansat al tendințelor climatice.

### 1.2. De ce Tema este Ideală pentru Evaluarea Universitară
Pentru o disciplină universitară de Programare Java (POO / Programare Avansată pe Obiecte), dezvoltarea unui sistem meteo este un vehicul didactic desăvârșit dintr-o multitudine de rațiuni:
1. **Model de Domeniu Bogat și Ierarhizat:** Fenomenele meteorologice necesită modelarea strictă a entităților (măsurători curente, prognoze agregate, coordonate GPS, alerte de risc, preferințe de utilizator), oferind terenul optim pentru demonstrarea principiilor fundamentale de încapsulare, imutabilitate (Java Records) și polimorfism.
2. **Nevoie Reală de Asincronism și Concurență:** Latența inerentă a apelurilor HTTP de rețea (100–1500 ms) impune separarea strictă a firelor de execuție: firul de prezentare grafică nu trebuie blocat niciodată, cererile de rețea trebuie executate non-blocant prin `CompletableFuture`, iar monitorizarea periodică a alertelor necesită planificatoare de fundal (`ScheduledExecutorService`).
3. **Persistență Duală (SQL + Fișiere):** Aplicația permite combinarea firească a persistenței relaționale prin JDBC nativ (istoric căutări, statistici, favorite) cu manipularea modernă a sistemului de fișiere prin Java NIO.2 (export rapoarte analitice în CSV și JSON).
4. **Tratare Exhaustivă a Erorilor:** Dependența de servicii de rețea externe oferă justificarea practică pentru o ierarhie solidă de excepții custom (rețea căzută, cheie API invalidă, oraș inexistent, răspuns JSON malformat, baze de date inaccesibile).
5. **Autonomie și Reproductibilitate:** Proiectul este conceput să funcționeze 100% autonom la corectare: utilizarea de baze de date embedded (SQLite / H2) și includerea unui furnizor fără cheie obligatorie (Open-Meteo) sau a unui mod demonstrativ Offline Mock garantează că profesorul poate descărca, compila și evalua aplicația instantaneu, fără a configura servere externe.

---

# 2. Analiza Comparativă a celor Trei Variante Arhitecturale

Pentru a oferi o viziune completă de inginerie software, prezentul proiect analizează trei arhitecturi alternative, fiecare răspunzând diferit compromisurilor dintre complexitate, vizibilitate conceptuală și portabilitate.

```
+----------------------------------------------------------------------------------------------------------------+
|                                    PANORAMĂ COMPARATIVĂ ARHITECTURALĂ                                          |
+------------------------------------+-------------------------------------------+-------------------------------+
|  VARIANTA 1: DESKTOP PURE JAVA     |  VARIANTA 2: WEB CLIENT-SERVER            |  VARIANTA 3: DATA-ORIENTED    |
|  (JavaFX / Swing + SQLite JDBC)    |  (Spring Boot 3.x + JPA + Thymeleaf/Web)  |  (Java Daemon + Raw JDBC +IPC)|
+------------------------------------+-------------------------------------------+-------------------------------+
| * Focus: Java SE Core & GUI Nativ  | * Focus: Arhitectură Enterprise & Cloud   | * Focus: Concurrency & Engine |
| * 100% Cod Java Vizibil & Controlat| * Framework-uri industriale (Spring)      | * Pipeline Producer-Consumer  |
| * Multithreading explicit pe EDT   | * Abstractizare declarativă prin IoC/AOP  | * Calcul analitic Z-Score     |
| * Conexiuni JDBC native clare      | * Bază de date H2 / PostgreSQL via ORM    | * Procesare loturi (batch)    |
| * Zero dependențe de servere web   | * Server HTTP Tomcat embedded             | * Integrare script Python/IPC |
+------------------------------------+-------------------------------------------+-------------------------------+
```

---

### 2.1. Varianta 1: Aplicație Desktop GUI Pur Java (JavaFX / Swing cu FlatLaf)

#### Stiva Tehnologică
* **Limbaj:** Java 17 sau Java 21 LTS (utilizând Java Records, Pattern Matching, Sealed Interfaces, Text Blocks).
* **Interfață Utilizator:**
  * *Opțiunea 1.A (Standard Modern):* **OpenJFX (JavaFX 21)** cu interfețe declarative în fișiere FXML, stilizare avansată prin CSS3 și data-binding reactiv (`ObservableList`, `StringProperty`).
  * *Opțiunea 1.B (Tradițională / Robustă):* **Java Swing standard** cu biblioteca de teme moderne **FlatLaf** (FlatLaf Dark Theme), oferind un aspect profesional și rulare directă din JDK.
* **Client HTTP:** `java.net.http.HttpClient` nativ (standardizat din Java 11, suport HTTP/2, conexiuni asincrone non-blocante prin `CompletableFuture`).
* **Parsare JSON:** `com.fasterxml.jackson.core:jackson-databind` (sau `org.json:json`).
* **Persistență Relațională:** `org.xerial:sqlite-jdbc` (driver JDBC pentru SQLite) fără server extern de baze de date. Baza locală este configurată riguros în regim concurent prin activarea modului **Write-Ahead Logging** (`PRAGMA journal_mode=WAL;`) și setarea unui timeout de așteptare a blocajelor (`PRAGMA busy_timeout=5000;`). Pentru a elimina complet riscul excepțiilor `SQLITE_BUSY: database is locked` la interogări asincrone paralele, operațiunile de scriere sunt serializate printr-un executor unifilar dedicat (`Executors.newSingleThreadExecutor()`), permițând citiri paralele nelimitate.
* **Persistență I/O Fișiere:** `java.nio.file.*` (Java NIO.2: `Files`, `Path`, `BufferedReader`, `BufferedWriter`).
* **Build Tool:** Apache Maven cu wrapper inclus (`mvnw` / `mvnw.cmd`).

#### Rolul Codului Java vs. Tehnologiile Complementare
În această variantă, **Java realizează 100% din sarcinile sistemului**:
* Desenează interfața grafică și gestionează arborele de componente (Scene Graph).
* Gestionează manual firele de execuție: firul de UI (JavaFX Application Thread / Swing EDT) nu este blocat niciodată; I/O-ul de rețea rulează pe pool-ul comun de asincronism sau pe un `ExecutorService` dedicat.
* Realizează maparea obiectuală între JSON-ul primit de la API și obiectele de domeniu interne.
* Construiește și execută manual comenzile SQL `PreparedStatement` pe baza de date locală SQLite, controlând tranzacțiile ACID și prevenind concurența distructivă pe fișierul bazei prin jurnalizare WAL și serializarea scrierilor.
Tehnologiile complementare sunt strict pasive: SQLite reprezintă un fișier pe disc accesat prin driver, iar fișierele FXML/CSS definesc doar layout-ul vizual.

#### Diagrama de Arhitectură Varianta 1
```
+----------------------------------------------------------------------------------------------------+
|                                ARHITECTURA DETALIATĂ A VARIANTEI 1                                 |
+----------------------------------------------------------------------------------------------------+
|                                                                                                    |
|    [ Utilizator ]                                                                                  |
|          │                                                                                         |
|          │ Interacțiune (Evenimente Mouse/Tastatură)                                               |
|          ▼                                                                                         |
|    +──────────────────────────────────────────────────────────────────────────────────────────+    |
|    |                             VIEW (JavaFX FXML / Swing FlatLaf)                           |    |
|    |  - MainWeatherDashboardView (Grafice de temperatură, câmp căutare, panou vreme curentă)  |    |
|    |  - FavoritesSidePanelView (Listă orașe favorite)                                         |    |
|    |  - HistoryTableView (Tabel măsurători istorice cu sortare)                               |    |
|    +─────────────────────────────────────────▲────────────────────────────────────────────────+    |
|                                              │ Data Binding / Platform.runLater()                  |
|                                              ▼                                                     |
|    +──────────────────────────────────────────────────────────────────────────────────────────+    |
|    |                                CONTROLLER (MVC / Presentation)                           |    |
|    |  - MainWeatherController (Ascultă acțiuni UI, validează input, inițiază operații async)  |    |
|    +─────────────────────────────────────────┬────────────────────────────────────────────────+    |
|                                              │                                                     |
|                                              ▼                                                     |
|    +──────────────────────────────────────────────────────────────────────────────────────────+    |
|    |                                BUSINESS SERVICE LAYER (Fațadă)                           |    |
|    |  - WeatherServiceFacade (Unifică cache-ul, clientul de rețea și repository-ul)          |    |
|    |  - WeatherAnalyticsService (Calcul medii, min/max prin Java Streams)                     |    |
|    |  - WeatherAlertEngine (Observer Subject care emite alerte la depășire praguri)          |    |
|    +─────────────────────┬──────────────────────────────────────────┬─────────────────────────+    |
|                          │                                          │                              |
|          (Cache Miss)    │                                          │ (Salvare Snapshot)           |
|                          ▼                                          ▼                              |
|    +───────────────────────────────────────+      +───────────────────────────────────────────+    |
|    |     NETWORKING & CACHING LAYER        |      |             PERSISTENCE LAYER             |    |
|    |  - CachingWeatherProxy                |      |  - WeatherSqliteRepository (Raw JDBC)     |    |
|    |    (ConcurrentHashMap cu TTL)         |      |    * PreparedStatement, ACID, SQLite      |    |
|    |  - WeatherApiClient (Strategy)        |      |  - UserPreferencesRepository (NIO.2 JSON) |    |
|    |    * OpenWeatherMapClient             |      |  - HistoryReportExporter (NIO.2 CSV)      |    |
|    |    * OpenMeteoClient (No-Key)         |      +─────────────────────┬─────────────────────+    |
|    |    * MockWeatherClient (Offline)      |                            │                          |
|    +───────────────────┬───────────────────+                            ▼                          |
|                        │ HTTP/2 GET                               [ Disc Local ]                   |
|                        ▼                                     - weather_data.db (SQLite)            |
|             [ API Meteorologic Extern ]                      - user_settings.json                  |
|             (OpenWeather / Open-Meteo)                       - export_prognoza.csv                 |
+----------------------------------------------------------------------------------------------------+
```

---

### 2.2. Varianta 2: Aplicație Web Client-Server (Spring Boot 3.x Backend + Frontend Web)

#### Stiva Tehnologică
* **Limbaj:** Java 17 / Java 21 LTS.
* **Core Framework:** Spring Boot 3.2+ (ecosistemul Spring Framework 6).
* **Module Spring Utilizate:**
  * `spring-boot-starter-web`: Conține containerul servlet embedded (Apache Tomcat), suport REST Controllers și serializare Jackson automată.
  * `spring-boot-starter-data-jpa`: Bază de date abstractizată prin Hibernate ORM și Spring Repositories.
  * `spring-boot-starter-validation`: Validare declarativă a parametrilor prin Jakarta Bean Validation (`@NotBlank`, `@Min`, `@Max`).
  * `spring-boot-starter-cache`: Gestiune unificată a memoriei cache cu implementarea de înaltă performanță `Caffeine`.
* **Bază de Date:** H2 Database (mod embedded/memorie cu zero instalare) sau PostgreSQL.
* **Prezentare UI (opțiuni):**
  * *Opțiunea A (Server-Side Rendering — Recomandată):* **Thymeleaf + Bootstrap 5**. Paginile HTML sunt generate complet pe server de Java; nu este necesar Node.js/npm.
  * *Opțiunea B (Single Page Application / REST):* REST API complet documentat interactiv prin **Springdoc OpenAPI (Swagger UI)** + pagină statică HTML/JavaScript cu Chart.js.

#### Rolul Java vs. Tehnologiile Complementare
* **Java (Spring Boot):** Reprezintă creierul întregului sistem server-side. Preia cererile HTTP pe portul 8080, le rutează prin filtre de securitate, gestionează tranzacțiile bazei de date prin proxy-uri generate la runtime (`@Transactional`), execută interogări polimorfice către API-uri meteo și programează sarcini recurente de fundal prin `@Scheduled`.
* **Tehnologii complementare:** Browserul web rulează interfața client, motorul de șabloane Thymeleaf randează HTML-ul, iar biblioteca Caffeine optimizează retenția obiectelor în memorie prin politici W-TinyLFU.

#### Diagrama de Arhitectură Varianta 2
```
+----------------------------------------------------------------------------------------------------+
|                                ARHITECTURA DETALIATĂ A VARIANTEI 2                                 |
+----------------------------------------------------------------------------------------------------+
|                                                                                                    |
|    [ Browser Web Client ]                                                                          |
|          │                                                                                         |
|          │ HTTP GET /weather?city=Cluj-Napoca (HTML sau JSON)                                      |
|          ▼                                                                                         |
|    +──────────────────────────────────────────────────────────────────────────────────────────+    |
|    |                            SPRING BOOT 3.x WEB ENGINE (Tomcat)                           |    |
|    |                                                                                          |    |
|    |  +───────────────────────────────+            +───────────────────────────────────────+  |    |
|    |  |     Thymeleaf UI Controller   |            |        OpenAPI REST Controller        |  |    |
|    |  |    (Randează dashboard.html)  |            |        (Expune /api/v1/weather)       |  |    |
|    |  +───────────────┬───────────────+            +───────────────────┬───────────────────+  |    |
|    |                  │                                                │                      |    |
|    |                  └───────────────────────┬────────────────────────┘                      |    |
|    |                                          ▼                                               |    |
|    |  +────────────────────────────────────────────────────────────────────────────────────+  |    |
|    |  |                              SPRING SERVICE LAYER                                  |  |    |
|    |  |  - WeatherOrchestrationService (@Service, orchestrare fluxuri)                     |  |    |
|    |  |  - ScheduledIngestionTask (@Scheduled cron = "0 */15 * * * *")                    |  |    |
|    |  |  - AlertNotificationDispatcher (Spring ApplicationEventPublisher)                  |  |    |
|    |  +───────────────┬────────────────────────────────────────────────┬───────────────────+  |    |
|    |                  │                                                │                      |    |
|    |                  ▼                                                ▼                      |    |
|    |  +───────────────────────────────+            +───────────────────────────────────────+  |    |
|    |  |      CAFFEINE CACHE LAYER     |            |       WEATHER ADAPTER (Strategy)      |  |    |
|    |  |  - In-Memory TTL (15 minute)  |            |  - OpenMeteoClient (Spring RestClient)|  |    |
|    |  |  - Rate-limit guard automat   |            |  - FallbackOpenWeatherClient          |  |    |
|    |  +───────────────────────────────+            +───────────────────┬───────────────────+  |    |
|    |                  │                                                │ HTTP JSON            |    |
|    |                  ▼                                                ▼                      |    |
|    |  +───────────────────────────────+                   [ Serviciu Meteo Public ]           |    |
|    |  |    SPRING DATA JPA LAYER      |                   (Open-Meteo / OpenWeather)          |    |
|    |  |  - WeatherRecordRepository    |                                                       |    |
|    |  |  - MonitoredCityRepository    |                                                       |    |
|    |  +───────────────┬───────────────+                                                       |    |
|    +──────────────────┼───────────────────────────────────────────────────────────────────────+    |
|                       │ HikariCP Connection Pool                                                   |
|                       ▼                                                                            |
|        +─────────────────────────────+                                                             |
|        |    Bază de Date Relațională |                                                             |
|        |     (H2 Embedded Memory)    |                                                             |
|        +─────────────────────────────+                                                             |
+----------------------------------------------------------------------------------------------------+
```

---

### 2.3. Varianta 3: Sistem Distribuit / Orientat pe Date (Java Core Daemon + Raw JDBC + Multithreading + Scripting IPC)

#### Stiva Tehnologică
* **Limbaj:** Java 17 / Java 21 LTS Core (Standard Edition — **fără framework-uri externe de tip Spring**).
* **Interfață Utilizator:** Linie de comandă (CLI) avansată, cu culori ANSI, tabele formatate și meniuri interactive bazate pe `java.util.Scanner` sau `picocli`.
* **Arhitectură Concurantă:** Pipeline clasic **Producer-Consumer** bazat pe `java.util.concurrent.BlockingQueue` și thread pool-uri explicate prin `ThreadPoolExecutor` și `ScheduledExecutorService`.
* **Persistență:** Raw JDBC cu `PreparedStatement`, execuție în loturi (`addBatch()`, `executeBatch()`), gestiune explicită a tranzacțiilor (`conn.setAutoCommit(false)`, `conn.commit()`, `conn.rollback()`), optimizat prin pool-ul de conexiuni `com.zaxxer:HikariCP`.
* **Motor Analitic & Matematic:** Pure Java (calcul de medii mobile, deviație standard, identificarea anomaliilor meteo extreme prin algoritmul statistic **Z-Score**).
* **Integrare Poliglotă & IPC (Inter-Process Communication):** `java.lang.ProcessBuilder` pentru generarea de grafice analitice externe printr-un script Python (`matplotlib`), dotat cu **mecanism robust de fallback nativ Java** (grafic ASCII randat direct în terminal dacă Python nu este instalat).

#### Rolul Java vs. Tehnologiile Complementare
* **Java:** Controlează întregul ciclu de viață al sistemului: inițializează firele paralele de achiziție, menține coada sincronizată de date, rulează algoritmii matematici de detecție a anomaliilor, coordonează persistența tranzacțională și orchestrează subprocesele sistemului de operare.
* **Tehnologii complementare:** Scriptul extern Python rulează într-un proces copil izolat strict pentru desenarea fișierelor grafice PNG. În caz de absență a interpretorului Python, modulul Java nativ preia sarcina și afișează curba direct în consolă.

#### Diagrama de Arhitectură Varianta 3
```
+----------------------------------------------------------------------------------------------------+
|                                ARHITECTURA DETALIATĂ A VARIANTEI 3                                 |
+----------------------------------------------------------------------------------------------------+
|                                                                                                    |
|    [ Utilizator / Administrator ]                                                                  |
|          │                                                                                         |
|          │ Comenzi Consolă CLI (ex: --monitor "Bucuresti,Cluj,Iasi" --interval 10)                 |
|          ▼                                                                                         |
|    +──────────────────────────────────────────────────────────────────────────────────────────+    |
|    |                      CLI CONTROLLER & INTERACTIVE TERMINAL DASHBOARD                     |    |
|    |  - AnsiTerminalRenderer (Tabele colorate ANSI, progress bars, alerte console)             |    |
|    +─────────────────────────────────────────┬────────────────────────────────────────────────+    |
|                                              │ Inițiază Pipeline                                   |
|                                              ▼                                                     |
|    +──────────────────────────────────────────────────────────────────────────────────────────+    |
|    |                       CONCURRENT DATA PIPELINE (PRODUCER-CONSUMER)                       |    |
|    |                                                                                          |    |
|    |    +──────────────────────────+                  +──────────────────────────+            |    |
|    |    |   WeatherFetcherWorker   |                  |   WeatherFetcherWorker   |            |    |
|    |    |   (Producer Thread #1)   |                  |   (Producer Thread #N)   |            |    |
|    |    +────────────┬─────────────+                  +────────────┬─────────────+            |    |
|    |                 │ HTTP GET                                    │ HTTP GET                 |    |
|    |                 └───────────────────────┬─────────────────────┘                          |    |
|    |                                         ▼                                                |    |
|    |                   +───────────────────────────────────────────+                          |    |
|    |                   |   ArrayBlockingQueue<RawWeatherData>      | (Buffer partajat capacitate)  |
|    |                   +─────────────────────┬─────────────────────+                          |    |
|    |                                         │ drainTo(batchList, 50)                         |    |
|    |                                         ▼                                                |    |
|    |                   +───────────────────────────────────────────+                          |    |
|    |                   |      BatchDatabasePersistenceWorker       |                          |    |
|    |                   |            (Consumer Thread)              |                          |    |
|    |                   +─────────────────────┬─────────────────────+                          |    |
|    +─────────────────────────────────────────┼────────────────────────────────────────────────+    |
|                                              │                                                     |
|                                              ▼                                                     |
|    +──────────────────────────────────────────────────────────────────────────────────────────+    |
|    |                                ADVANCED ANALYTICAL ENGINE                                |    |
|    |  - StatisticalEngine: Calcul Medie Aritmetică, Deviație Standard (StdDev)                 |    |
|    |  - AnomalyDetector: Calcul Scor Z (Z = |X - μ| / σ); Flag dacă Z >= 2.5 (Vreme Extremă)   |    |
|    |  - HeatIndexCalculator: Formule polinomiale de confort termic                             |    |
|    +─────────────────────┬──────────────────────────────────────────┬─────────────────────────+    |
|                          │                                          │                              |
|                          ▼                                          ▼                              |
|    +───────────────────────────────────────+      +───────────────────────────────────────────+    |
|    |       RAW JDBC PERSISTENCE            |      |         IPC BRIDGE & PLOTTING             |    |
|    |  - HikariCP Connection Pool          |      |  - ProcessBuilder ("python chart.py")     |    |
|    |  - PreparedStatement (addBatch)       |      |  - Interceptare stdout/stderr             |    |
|    |  - Tranzacții manuale (commit/rollb.) |      |  - FALLBACK ROBUST INTEGRAT:              |    |
|    +───────────────────┬───────────────────+      |    * AsciiTerminalPlotter (Java Nativ)    |    |
|                        │                          +─────────────────────┬─────────────────────+    |
|                        ▼                                                ▼                          |
|             [ SQLite / MySQL DB ]                               [ Ieșire Vizuală ]                 |
|             - telemetry_records                                 - chart_evolutie.png (via Python)  |
|             - detected_anomalies                                - [===*====] Grafic ASCII Consola  |
+----------------------------------------------------------------------------------------------------+
```

---

### 2.4. Tabel Comparativ Sintetic și Matrice Multicriterială

Următorul tabel oferă profesorului o comparație directă între cele trei variante tehnologice propuse:

| Criteriu de Evaluare | Varianta 1: Desktop GUI (JavaFX / Swing) | Varianta 2: Web Client-Server (Spring Boot) | Varianta 3: Data Daemon (Java SE Core + IPC) |
|---|---|---|---|
| **Paradigmă Principală** | Orientată pe Obiecte, Event-Driven, Desktop MVC | Arhitectură Enterprise pe Straturi, REST / Web | Programare Concurentă, Data Pipeline & Analitică |
| **Interfață Utilizator** | Grafică Desktop nativă (FXML / CSS / FlatLaf) | Browser Web (HTML5/Thymeleaf sau REST + Swagger) | Consolă CLI interactivă ANSI + Grafice ASCII / PNG |
| **Mecanism Concurrency** | `CompletableFuture` + Event Thread (JavaFX/EDT) | `@Async`, ThreadPool Spring, `@Scheduled` crons | `BlockingQueue`, `ThreadPoolExecutor`, Daemon Workers |
| **Persistență de Date** | SQLite local prin JDBC nativ + fișiere NIO.2 | H2 / PostgreSQL via Spring Data JPA (Hibernate) | SQLite prin JDBC pur, batch inserts și tranzacții |
| **Complexitate Tehnică** | **Medie** (echilibrată, didactică, control direct) | **Medie-Ridicată** (abstracțiuni multiple de framework) | **Ridicată** (sincronizare fire, procese externe, algoritmi) |
| **Efort Estimativ Dezvoltare** | **25 – 35 ore de lucru** | **30 – 40 ore de lucru** | **30 – 42 ore de lucru** |
| **Dependențe Externe** | Minime (doar SQLite JDBC, JavaFX, Jackson) | Medii (ecosistemul complet Spring Boot, Hibernate) | Minime (doar SQLite JDBC, HikariCP, Jackson) |
| **Acoperire Concepte Java SE** | **100% Transparentă** (fără cod mascat de framework)| **90%** (o parte din logică e preluată de adnotări) | **100% Explicită** (management manual complet) |
| **Risc de Rulare la Notare** | **Nul** (proiect autonom, rulabil cu `mvn javafx:run`)| **Foarte Scăzut** (Tomcat și H2 embedded în JAR) | **Scăzut** (datorită fallback-ului Java nativ la grafic)|
| **Relevanță Academică POO/PAO**| **Maximă (Nota 10)** — ideală pentru colocviu | Excelentă pentru cursuri de Tehnologii Enterprise | Excelentă pentru sisteme de operare / concurență |

---

### 2.5. Varianta Recomandată pentru Curs și Argumentarea Academică

> **Recomandarea Oficială pentru Proiect:** **Varianta 1 (Aplicație Desktop Pur Java — JavaFX / Swing cu FlatLaf, SQLite JDBC și CompletableFuture)**.

#### Argumentare Academică și Metodologică:
1. **Transparență Conceptuală Totală:** Într-un examen sau colocviu universitar de Java, comisia dorește să vadă cum stăpânește studentul conceptele fundamentale: cum se instantiază și se sincronizează un fir de execuție, cum se previne SQL Injection prin `PreparedStatement`, cum se implementează șablonul Observer fără biblioteci externe și cum se transmit datele între componente prin polimorfism pur. În Varianta 1, fiecare linie de cod aparține autorului, fără „magia” injectării de dependențe sau a proxy-urilor AOP generate automat de Spring Boot.
2. **Eliminarea Riscului Tehnic la Evaluare:** Varianta 1 rulează într-o singură Mașină Virtuală Java (JVM), pe orice sistem de operare (Windows, Linux, macOS), fără conflicte de porturi de rețea (ex. portul 8080 deja ocupat pe calculatorul cadrului didactic), fără dependențe de Node.js sau baze de date externe.
3. **Impact Vizual și Interactivitate:** O interfață desktop realizată îngrijit (JavaFX cu temă întunecată sau Swing FlatLaf) oferă o experiență de utilizare extrem de fluidă și modernă, permițând demonstrarea live a reacției interfeței la evenimente asincrone (spinners, actualizări de grafice de temperatură în timp real, notificări toast).

*Notă de Flexibilitate:* Dacă programa cursului la care se depune prezentul proiect pune accent expres pe tehnologii de nivel Enterprise sau dezvoltare Backend, studentul poate comuta cu ușurință pe **Varianta 2 (Spring Boot)**, codul de business și modelele de domeniu fiind identice în proporție de peste 80%.

---

# 3. Integrarea cu API-uri Meteorologice Publice & Strategii de Fiabilitate

### 3.1. Analiza Comparativă a Furnizorilor de Date Meteorologice

Pentru a asigura rezistența la indisponibilități temporare și a oferi o flexibilitate maximă la testare, sistemul decuplează accesul la date printr-o interfață `WeatherApiClient` și suportă trei furnizori:

```
+---------------------------------------------------------------------------------------------------------------+
|                                    ANALIZA COMPARATIVĂ A API-URILOR METEO                                     |
+----------------------+-----------------------------+-----------------------------+----------------------------+
| Caracteristică       | 1. OpenWeatherMap           | 2. Open-Meteo (RECOMANDAT)  | 3. WeatherAPI.com          |
+----------------------+-----------------------------+-----------------------------+----------------------------+
| **Necesitate Cheie** | Necesită API Key (Gratuit)  | **FĂRĂ CHEIE (Zero Setup)** | Necesită API Key (Gratuit) |
| **Limită Apeluri**   | 60 cereri / minut (1000/zi) | Fără limită strictă rigidă  | 1.000.000 cereri / lună    |
| **Date Oferite**     | Curent, Prognoză 5z, Aer    | Curent, Prognoză 7z, Orare  | Curent, Prognoză 3z, Radar |
| **Format Răspuns**   | JSON complex, standardizat  | JSON foarte clar și compact | JSON compact, bine struct. |
| **Rol în Proiect**   | Furnizor Principal          | **Furnizor Rapid Evaluare** | Furnizor Secundar / Backup |
+----------------------+-----------------------------+-----------------------------+----------------------------+
```

* **Beneficiu Critic pentru Notare (Open-Meteo):** Datorită faptului că furnizorul `Open-Meteo` nu solicită absolut nicio cheie de autentificare, **cadrul didactic poate rula proiectul instantaneu la corectare**, fără a fi obligat să își creeze conturi pe platforme terțe sau să modifice fișiere de proprietăți.

---

### 3.2. Fluxul de Date End-to-End

Următoarea diagramă de secvență ilustrează traseul parcurs de un pachet de date, de la interacțiunea utilizatorului până la afișarea pe ecran și stocarea pe disc:

```
[ Utilizator ]     [ UI View ]       [ Controller ]     [ WeatherService ]     [ Cache Proxy ]     [ API Client ]     [ DB Repository ]
      │                 │                  │                     │                     │                  │                  │
      │ 1. Click Caută  │                  │                     │                     │                  │                  │
      ├────────────────►│                  │                     │                     │                  │                  │
      │                 │ 2. onSearch()    │                     │                     │                  │                  │
      │                 ├─────────────────►│                     │                     │                  │                  │
      │                 │                  │ 3. Afișează Spinner │                     │                  │                  │
      │                 │                  ├────────────────────►│                     │                  │                  │
      │                 │                  │                     │ 4. fetchAsync(oras) │                  │                  │
      │                 │                  │                     ├────────────────────►│                  │                  │
      │                 │                  │                     │                     │ 5. get(oras)     │                  │
      │                 │                  │                     │                     ├─────────┐        │                  │
      │                 │                  │                     │                     │         │ Verifică                  │
      │                 │                  │                     │                     │<────────┘ TTL    │                  │
      │                 │                  │                     │                     │                  │                  │
      │                 │                  │                     │  [Dacă NU e în Cache / Expirat]        │                  │
      │                 │                  │                     │                     │ 6. HTTP GET req  │                  │
      │                 │                  │                     │                     ├─────────────────►│                  │
      │                 │                  │                     │                     │                  │ 7. HTTP/2 Call   │
      │                 │                  │                     │                     │                  ├─────────┐ (Web)  │
      │                 │                  │                     │                     │                  │<────────┘ Răspuns│
      │                 │                  │                     │                     │ 8. JSON Response │                  │
      │                 │                  │                     │                     │<─────────────────┤                  │
      │                 │                  │                     │                     │                  │                  │
      │                 │                  │                     │ 9. WeatherReportDTO │                  │                  │
      │                 │                  │                     │<────────────────────┤                  │                  │
      │                 │                  │                     │                     │ (Salvare în mem) │                  │
      │                 │                  │                     │                     │                  │                  │
      │                 │                  │                     │ 10. Salvează snapshot asincron         │                  │
      │                 │                  │                     ├──────────────────────────────────────────────────────────►│
      │                 │                  │                     │                     │                  │                  │ (INSERT JDBC)
      │                 │                  │ 11. CompletableFuture.thenAccept()        │                  │                  │
      │                 │                  │<────────────────────┤                     │                  │                  │
      │                 │                  │                     │                     │                  │                  │
      │                 │ 12. Platform.runLater() [EDT Sync]     │                     │                  │                  │
      │                 │◄─────────────────┤                     │                     │                  │                  │
      │                 │                  │                     │                     │                  │                  │
      │ 13. Vremea afișată (Stop Spinner)  │                     │                     │                  │                  │
      │◄────────────────┤                  │                     │                     │                  │                  │
```

---

### 3.3. Caching Inteligent în Memorie cu TTL (Time-To-Live)

Pentru a asigura o experiență de utilizare instantanee, a preveni blocarea contului gratuit de API prin "Rate Limiting" și a demonstra conceptele avansate de colecții concurente, aplicația integrează un **Proxy de Caching cu TTL**.

```
+----------------------------------------------------------------------------------------------------+
|                               MECANISMUL DE CACHING CU CONCURRENT HASH MAP                         |
+----------------------------------------------------------------------------------------------------+
|                                                                                                    |
|    Cheie Căutare: "bucuresti" (Normalizată: trim + lower-case)                                     |
|                                                                                                    |
|    +──────────────────────────────────────────────────────────────────────────────────────────+    |
|    |                        ConcurrentHashMap<String, CachedWeatherEntry>                     |    |
|    +──────────────────────────────────────────────────────────────────────────────────────────+    |
|    |  "bucuresti"  ──►  [ WeatherReport: 18.5°C, 62% ]  |  Created: 14:00  |  Expires: 14:15     |    |
|    |  "cluj-napoca"──►  [ WeatherReport: 15.2°C, 75% ]  |  Created: 13:50  |  Expires: 14:05 (EXP)|    |
|    |  "iasi"       ──►  [ WeatherReport: 17.0°C, 58% ]  |  Created: 14:10  |  Expires: 14:25     |    |
|    +──────────────────────────────────────────────────────────────────────────────────────────+    |
|                                                                                                    |
|    Mecanism Tehnic:                                                                                |
|    1. Căutare O(1) în ConcurrentHashMap.                                                          |
|    2. Verificare: Instant.now().isBefore(entry.getExpiresAt()).                                    |
|    3. Dacă este VALID: AtomicInteger cacheHits.incrementAndGet() -> Returnează instantaneu (<1ms).|
|    4. Dacă este EXPIRAT: cacheMisses.incrementAndGet() -> Apelează serviciul real -> Înlocuiește.  |
|    5. Evacuare automată în fundal: ScheduledExecutor curăță periodic intrările expirate.          |
+----------------------------------------------------------------------------------------------------+
```

* **Formulă de Eficiență Academică (Cache Hit Ratio):**
  $$\text{Hit Ratio} = \frac{\text{Cache Hits}}{\text{Cache Hits} + \text{Cache Misses}} \times 100\%$$
  Aplicația expune această valoare în ecranul de diagnoză tehnică, demonstrând eficiența mecanismului.

---

### 3.4. Reziliență: Mod Offline și MockWeatherService pentru Evaluare

Dacă mașina de evaluare nu are conexiune activă la internet sau dacă API-ul extern întâmpină dificultăți de rețea, aplicația **nu se prăbușește niciodată**. Ea integrează două straturi robuste de protecție:
1. **Fallback pe Baza de Date Locală (Offline Cache):** Dacă apelul extern eșuează cu `NetworkException`, aplicația caută automat în baza SQLite ultima măsurătoare salvată pentru acel oraș și o afișează, însoțită de un avertisment vizual galben: *"Date încărcate din istoricul local (Offline)"*.
2. **`MockWeatherService` (Comutator Didactic):** Printr-o simplă bifă în meniul de setări sau un argument la pornire (`--mock`), aplicația activează un furnizor de simulare care generează măsurători deterministe pentru 10 orașe din România, permițând testarea tuturor ecranelor grafice, alertelor și rapoartelor fără nicio conexiune externă.

---

# 4. Matricea Exhaustivă de Corespondență a Conceptelor Academice Java

Această secțiune constituie **inima tehnică a propunerii de proiect**, demonstrând modul în care fiecare cerință a curriculei universitare este acoperită prin cod Java riguros și modern.

### 4.1. Tabel Sinoptic Curricular: Concepte Teoretice vs. Implementare Concretă

| Categorie Curriculară | Concept Teoretic Predat | Clasă / Interfață de Referință (`ro.univ.weather.*`) | Rol Concret și Mecanism Tehnic în Aplicație |
|---|---|---|---|
| **OOP: Încapsulare** | Câmpuri private, imutabilitate, validare invariantă, `record` | `model.WeatherData`, `model.Coordinates` | Obiecte imutabile cu validarea domeniului fizic (ex: umiditate între 0% și 100%, temperaturi $> -273.15^\circ\text{C}$). |
| **OOP: Moștenire** | Clase abstracte, reutilizare de cod, apeluri `super()` | `client.AbstractWeatherApiClient` | Centralizează configurarea clientului HTTP, managementul timeout-urilor și logica comună de retry. |
| **OOP: Polimorfism** | Interfețe, dynamic dispatch, decuplare arhitecturală | `client.WeatherApiClient` | Permite comutarea transparentă la runtime între OpenWeather, Open-Meteo și Mock prin aceeași interfață. |
| **OOP: Abstractizare** | Separarea contractului de reprezentarea internă | `repository.WeatherRepository` | Decuplează logica de stocare (SQLite vs fișiere) de consumatorii din stratul de business. |
| **Colecții: `List<T>`** | `ArrayList`, indexare rapidă $O(1)$, ordonare temporală | `model.WeatherForecast` (menține `List<ForecastItem>`) | Stochează prognozele orare pe următoarele 5 zile, permițând iterație directă în controalele grafice UI. |
| **Colecții: `Set<T>`** | `HashSet`, unicitate $O(1)$, contractul `equals`/`hashCode` | `service.FavoritesManager` (`Set<String> favoriteCities`) | Garantează că un oraș nu poate fi adăugat de două ori la favorite; normalizare prin `toLowerCase()`. |
| **Colecții: `Map<K, V>`** | `ConcurrentHashMap`, asociere cheie-valoare, concurență | `service.CachingWeatherProxy` (`Map<String, CacheEntry>`) | Cache de înaltă viteză pentru rezultatele interogărilor meteo; previne apelurile externe redundante. |
| **Colecții: `PriorityQueue`**| Heap binar, ordonare automată pe bază de prioritate | `service.WeatherAlertEngine` (`PriorityQueue<WeatherAlert>`) | Extrage întotdeauna cu prioritate maximă alertele meteo critice (ex: Cod Roșu de Vânt înaintea unei avertizări simple). |
| **Colecții: Comparatori**| `Comparator.comparingDouble()`, sortare compusă | `util.WeatherComparators` | Sortarea dinamică a orașelor după temperatură descrescătoare, nivel de umiditate sau dată calendaristică. |
| **Streams & Lambdas** | `filter()`, `map()`, `collect()`, `groupingBy()`, `reduce()` | `service.WeatherAnalyticsService` | Calculul mediei zilnice, identificarea maximelor, gruparea măsurătorilor pe zile calendaristice. |
| **Tratarea Excepțiilor**| Ierarhie custom Checked & Unchecked, exception chaining | `exception.WeatherAppException` și subclasele sale | Izolarea clară a problemelor: `CityNotFoundException` (404), `ApiKeyInvalidException` (401), `NetworkException`. |
| **Gestiune Resurse** | Idiomul `try-with-resources`, interfața `AutoCloseable` | `repository.JdbcWeatherRepository`, `export.CsvExporter` | Închiderea automată și sigură a conexiunilor JDBC, a `PreparedStatement`-urilor și a fișierelor pe disc. |
| **I/O Fișiere (NIO.2)** | `Path`, `Files`, `BufferedWriter`, `StandardOpenOption` | `export.CsvWeatherExporter`, `export.JsonExporter` | Generarea pe disc a rapoartelor meteorologice complete pentru analiză ulterioară în Excel / Python. |
| **Persistență JDBC** | `Connection`, `PreparedStatement`, `ResultSet`, ACID | `repository.JdbcWeatherRepository` | Executarea interogărilor SQL parametrizate pentru prevenirea SQL Injection și tranzacții atomice locale. |
| **Concurrency & Async**| `CompletableFuture`, `ForkJoinPool`, `ScheduledExecutor` | `client.OpenMeteoClient`, `service.WeatherPollerService` | Execuție de rețea pe fire paralele non-blocante și reîmprospătare automată a datelor la fiecare 15 minute. |
| **Sincronizare UI** | Trecerea sigură a datelor de pe worker threads pe UI | `ui.controller.MainWeatherController` | Utilizarea `Platform.runLater()` (JavaFX) sau `SwingUtilities.invokeLater()` pentru a preveni crash-ul UI. |
| **Creational Patterns** | **Singleton**, **Factory Method**, **Builder** | `config.AppConfig`, `client.WeatherClientFactory` | Gestiune unică configurație, construire de rapoarte cu mulți parametri, fabricare clienți polimorfici. |
| **Structural Patterns** | **Adapter**, **Facade**, **Decorator** | `adapter.OpenWeatherAdapter`, `service.WeatherServiceFacade` | Transformare JSON terț în entitate internă; ascunderea complexității subsystemelor în spatele unei fațade. |
| **Behavioral Patterns**| **Observer**, **Strategy** | `event.WeatherEventPublisher`, `strategy.ForecastStrategy` | Decuplarea notificărilor UI la sosirea datelor; algoritmi interschimbabili de predicție a vremii. |
| **Calitate & Testare** | Testare unitară (JUnit 5), izolarea dependențelor (Mockito)| `src/test/java/ro/univ/weather/...` | Verificarea corectitudinii algoritmilor și a ierarhiei de excepții independent de conexiunea la internet. |

---

### 4.2. Programare Orientată pe Obiecte (OOP)

#### A. Încapsulare și Imutabilitate prin Java Records
Pentru obiectele de transfer și entitățile de domeniu, se utilizează facilitatea modernă **Java Records** (sau clase finale cu atribute `private final` și validare de invarianți):
```java
package ro.univ.weather.model;

import ro.univ.weather.exception.InvalidMeasurementException;
import java.time.Instant;
import java.util.Objects;

public record WeatherData(
    String cityName,
    double temperature,
    double feelsLike,
    int humidity,
    double windSpeedKmH,
    double pressureHpa,
    WeatherCondition condition,
    Instant timestamp
) {
    // Constructor compact pentru validarea invariantilor de business
    public WeatherData {
        Objects.requireNonNull(cityName, "Numele orasului nu poate fi nul");
        Objects.requireNonNull(condition, "Conditia meteorologica nu poate fi nula");
        
        if (cityName.isBlank()) {
            throw new InvalidMeasurementException("Numele orasului nu poate fi vid");
        }
        if (temperature < -90.0 || temperature > 65.0) {
            throw new InvalidMeasurementException("Temperatura este in afara limitelor terestre posibile: " + temperature);
        }
        if (humidity < 0 || humidity > 100) {
            throw new InvalidMeasurementException("Umiditatea trebuie sa fie intre 0% si 100%: " + humidity);
        }
        if (windSpeedKmH < 0.0) {
            throw new InvalidMeasurementException("Viteza vantului nu poate fi negativa");
        }
        if (timestamp == null) {
            timestamp = Instant.now();
        }
    }
}
```

#### B. Abstractizare, Moștenire și Polimorfism
Interfața `WeatherApiClient` definește contractul de comunicare, iar clasa abstractă `AbstractWeatherApiClient` extrage funcționalitățile comune:
```java
package ro.univ.weather.client;

import ro.univ.weather.model.WeatherData;
import ro.univ.weather.model.WeatherForecast;
import java.util.concurrent.CompletableFuture;

public interface WeatherApiClient {
    CompletableFuture<WeatherData> fetchCurrentWeatherAsync(String cityName);
    CompletableFuture<WeatherForecast> fetchForecastAsync(String cityName);
    String getProviderName();
}
```

Implementările concrete (`OpenMeteoClient`, `OpenWeatherMapClient`, `MockWeatherClient`) moștenesc `AbstractWeatherApiClient`, fiind instanțiate polimorfic prin `WeatherClientFactory`.

---

### 4.3. Corespondența Structurilor de Date și a Colecțiilor Java

Aplicația folosește structuri de date adaptate specificului fiecărei probleme:
1. **`List<ForecastItem>` (`ArrayList`):** Asigură accesul instantaneu prin index $O(1)$ la cele 40 de eșantioane din prognoza pe 5 zile (intervale de 3 ore), esențial pentru desenarea rapidă a graficelor.
2. **`Set<String>` (`HashSet`):** Menține lista orașelor favorite. Normalizarea prin `cityName.trim().toLowerCase()` combinată cu implementarea corectă a `equals()` și `hashCode()` garantează că nu există duplicate.
3. **`Map<String, CachedWeatherEntry>` (`ConcurrentHashMap`):** Asigură acces thread-safe în timp $O(1)$ la răspunsurile stocate în memoria cache.
4. **`PriorityQueue<WeatherAlert>` (Min/Max Heap):** Alertele meteorologice implementează `Comparable<WeatherAlert>` în funcție de nivelul de severitate (`SEVERITY_EMERGENCY`, `SEVERITY_WARNING`, `SEVERITY_INFO`). Alertele critice sunt extrase și afișate primele.

---

### 4.4. Procesare Declarativă cu Java Streams & Expresii Lambda

Toate agregările de date din modulul `WeatherAnalyticsService` sunt scrise folosind Streams API, demonstrând cunoașterea stilului de programare funcțională:
```java
package ro.univ.weather.service;

import ro.univ.weather.model.ForecastItem;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class WeatherAnalyticsService {

    // 1. Calculul statistic al temperaturilor (Min, Max, Medie) pe un set de prognoze
    public DoubleSummaryStatistics calculateTemperatureStatistics(List<ForecastItem> items) {
        return items.stream()
            .mapToDouble(ForecastItem::temperature)
            .summaryStatistics();
    }

    // 2. Gruparea prognozelor pe zile calendaristice folosind groupingBy
    public Map<LocalDate, List<ForecastItem>> groupForecastByDay(List<ForecastItem> items) {
        return items.stream()
            .collect(Collectors.groupingBy(
                item -> item.timestamp().atZone(ZoneId.systemDefault()).toLocalDate(),
                TreeMap::new, // Pastreaza zilele ordonate cronologic
                Collectors.toList()
            ));
    }

    // 3. Identificarea intervalelor orare cu risc ridicat de precipitatii (filtrare si sortare)
    public List<ForecastItem> findHighRainRiskPeriods(List<ForecastItem> items, double minPrecipitationMm) {
        return items.stream()
            .filter(item -> item.precipitationMm() >= minPrecipitationMm)
            .sorted(Comparator.comparingDouble(ForecastItem::precipitationMm).reversed())
            .toList();
    }
}
```

---

### 4.5. Ierarhia Custom de Excepții și Managementul Resurselor

O cerință de bază în mediul academic este crearea unei ierarhii coerente de excepții, decuplând erorile de sistem de mesajele prezentate utilizatorului.

```
                               +-----------------------------+
                               |     java.lang.Exception     |
                               +-----------------------------+
                                              │
                                              ▼
                               +-----------------------------+
                               |  WeatherAppException (root) |
                               +-----------------------------+
                                              │
                    ┌─────────────────────────┼─────────────────────────┐
                    ▼                         ▼                         ▼
     +-----------------------------+ +------------------+ +-----------------------------+
     |     WeatherApiException     | | StorageException | |  InvalidMeasurementException|
     |   (Erori HTTP, 4xx, 5xx)    | | (JDBC, File I/O) | |     (Checked / Domain)      |
     +-----------------------------+ +------------------+ +-----------------------------+
                    │
         ┌──────────┴──────────┐
         ▼                     ▼
+------------------+ +--------------------+
| CityNotFoundEx.  | | RateLimitExceededEx|
|    (HTTP 404)    | |     (HTTP 429)     |
+------------------+ +--------------------+
```

#### Exemplu Implementare Excepții:
```java
package ro.univ.weather.exception;

public class WeatherAppException extends Exception {
    public WeatherAppException(String message) { super(message); }
    public WeatherAppException(String message, Throwable cause) { super(message, cause); }
}

public class CityNotFoundException extends WeatherAppException {
    private final String requestedCity;
    public CityNotFoundException(String city) {
        super("Orasul '" + city + "' nu a fost gasit in baza de date a serviciului meteo.");
        this.requestedCity = city;
    }
    public String getRequestedCity() { return requestedCity; }
}
```

Toate operațiunile cu fișiere sau conexiuni de baze de date utilizează cu strictețe structura `try-with-resources` (`AutoCloseable`):
```java
try (Connection conn = connectionManager.getConnection();
     PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
    // Operatiuni JDBC garantate fara scurgeri de conexiuni
    pstmt.executeUpdate();
} catch (SQLException e) {
    throw new StorageException("Eroare la persistarea datelor meteo", e);
}
```

---

### 4.6. Persistență Relațională (JDBC) și Operațiuni Moderne I/O (NIO.2)

#### Schemă de Bază de Date Relațională SQLite (DDL):
```sql
CREATE TABLE IF NOT EXISTS favorite_cities (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    city_name TEXT NOT NULL UNIQUE,
    latitude REAL,
    longitude REAL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS weather_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    city_name TEXT NOT NULL,
    temperature REAL NOT NULL,
    feels_like REAL NOT NULL,
    humidity INTEGER NOT NULL,
    wind_speed REAL NOT NULL,
    condition_text TEXT NOT NULL,
    recorded_at TIMESTAMP NOT NULL
);
```

#### Persistență I/O Modernă prin Java NIO.2:
Modulul de export generează rapoarte tabelare CSV folosind API-ul `java.nio.file`:
```java
package ro.univ.weather.export;

import ro.univ.weather.model.ForecastItem;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvWeatherExporter implements WeatherExporter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public void exportForecast(List<ForecastItem> items, Path destination) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(destination, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            
            writer.write("Timestamp;Temperatura_C;Resimtita_C;Umiditate_Procent;Vant_KmH;Conditie");
            writer.newLine();

            for (ForecastItem item : items) {
                String line = String.format("%s;%.1f;%.1f;%d;%.1f;%s",
                    FORMATTER.format(item.timestamp().atZone(java.time.ZoneId.systemDefault())),
                    item.temperature(), item.feelsLike(), item.humidity(), item.windSpeedKmH(), item.condition().name());
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
```

---

### 4.7. Fire de Execuție, Concurență și Asincronism Non-blocant

Interfața grafică a aplicației este menținută complet fluidă prin delegarea apelurilor I/O pe worker threads:
1. **`CompletableFuture.supplyAsync()`:** Realizează cererea HTTP și parsarea JSON-ului pe un thread pool de fundal.
2. **`Platform.runLater(Runnable)` (sau `SwingUtilities.invokeLater`):** Readuce rezultatul procesat pe firul grafic UI principal, prevenind excepția `IllegalStateException: Not on FX application thread`.
3. **`ScheduledExecutorService` (Auto-Refresh):** Un daemon de fundal interoghează periodic starea vremii pentru orașul activ:
```java
ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
    Thread t = new Thread(r, "Weather-AutoRefresh-Daemon");
    t.setDaemon(true); // Permite inchiderea aplicatiei fara fire blocate
    return t;
});
scheduler.scheduleAtFixedRate(this::refreshActiveCityData, 15, 15, TimeUnit.MINUTES);
```

---

### 4.8. Șabloane de Proiectare Software (Design Patterns GoF)

Proiectul integrează în mod organic 8 șabloane de proiectare fundamentale:

```
+---------------------------------------------------------------------------------------------------------------+
|                                ȘABLOANE DE PROIECTARE (DESIGN PATTERNS GoF)                                   |
+--------------------+----------------------------+-------------------------------------------------------------+
| Categorie GoF      | Șablon Utilizat            | Clasă / Componentă Concretă în Proiect                      |
+--------------------+----------------------------+-------------------------------------------------------------+
| **Creaționale**    | **Singleton**              | `config.AppConfig`, `repository.DatabaseConnectionManager`   |
|                    | **Factory Method**         | `client.WeatherClientFactory` (creează providerul activ)     |
|                    | **Builder**                | `model.WeatherReport.Builder` (obiect complex cu 12 câmpuri) |
+--------------------+----------------------------+-------------------------------------------------------------+
| **Structurale**    | **Adapter**                | `adapter.OpenWeatherAdapter` (DTO JSON terț -> Model intern)|
|                    | **Facade**                 | `service.WeatherServiceFacade` (unifică Cache, API și DB)   |
|                    | **Decorator**              | `client.CachedWeatherClientDecorator` (caching transparent) |
+--------------------+----------------------------+-------------------------------------------------------------+
| **Comportamentale**| **Observer**               | `event.WeatherEventPublisher` & `event.WeatherObserver`     |
|                    | **Strategy**               | `strategy.ForecastStrategy` (algoritmi diferiți de calcul)  |
+--------------------+----------------------------+-------------------------------------------------------------+
```

---

### 4.9. Calitatea Codului, Testare Unitară (JUnit 5 & Mockito) și Documentare

* **Acoperire de Testare:** Minimum 20 de teste unitare în `src/test/java/ro/univ/weather/` acoperind:
  * Validarea modelelor de domeniu (aruncarea excepțiilor la valori fizice imposibile).
  * Algoritmii statistici din `WeatherAnalyticsService`.
  * Funcționarea cache-ului TTL (hit/miss/expirare).
  * Simularea comportamentului rețelei prin mock-uri create cu Mockito (`when(client.fetchCurrentWeatherAsync(...)).thenReturn(...)`).
* **Documentație Javadoc:** Toate interfețele, clasele și metodele publice includ comentarii structurate (`@param`, `@return`, `@throws`).

---

# 5. Descrierea Formală a Proiectului pentru Profesor (Fișa Oficială de Aprobare)

---

### FIȘĂ OFICIALĂ DE PROPUNERE ȘI VALIDARE A TEMEI DE PROIECT

#### A. Date Student & Coordonator
* **Titlu Proiect Propus:** **GeoWeather Pro — Sistem Integrat de Monitorizare, Prognoză și Analiză Meteorologică în Java**
* **Nume și Prenume Student:** [Nume și Prenume Student]
* **Grupa / Semigrupa / Specializarea:** Grupa [XX], Anul III, Informatică / Calculatoare
* **Cadrul Didactic Îndrumător:** Prof. Dr. Ing. / Conf. Univ. Dr. [Nume Coordonator]
* **Anul Universitar:** 2026 – 2027, Semestrul I

---

### 5.1. Obiectivele Generale și Specifice de Învățare

#### Obiectivul General
Proiectarea, dezvoltarea și testarea unei aplicații software complete în limbajul Java, având ca scop achiziția în timp real, analiza statistică, vizualizarea grafică și persistența locală a datelor meteorologice furnizate de servicii REST web publice.

#### Obiective Specifice
1. Demonstrarea stăpânirii principiilor fundamentale ale Programării Orientate pe Obiecte (încapsulare, abstractizare, moștenire, polimorfism) prin crearea unui model de domeniu imutabil și extensibil.
2. Implementarea unei arhitecturi asincrone non-blocante prin intermediul `CompletableFuture` și sincronizarea corectă cu firul de execuție al interfeței grafice.
3. Asigurarea persistenței locale duale: baze de date relaționale prin JDBC nativ (SQLite) și sistem de fișiere prin Java NIO.2 (rapoarte CSV/JSON).
4. Integrarea șabloanelor de proiectare consacrate (MVC, Observer, Singleton, Factory Method, Adapter, Facade, Strategy, Decorator) pentru a asigura un cod curat, decuplat și testabil.
5. Furnizarea unei suite de teste automate prin JUnit 5 și Mockito, asigurând funcționarea autonomă a aplicației în mod demonstrativ/mock.

---

### 5.2. Specificația Cerințelor Funcționale (Modelul MoSCoW)

```
+----------------------------------------------------------------------------------------------------+
|                           CLASIFICAREA CERINȚELOR FUNCȚIONALE (MoSCoW)                             |
+----------------------------------------------------------------------------------------------------+
|  [MUST-HAVE / MVP OBLIGATORIU]                                                                     |
|  - F1. Căutare vreme curentă după numele orașului (București, Cluj, Timișoara, etc.).              |
|  - F2. Afișare parametri meteo detaliați: temperatură, resimțită, umiditate, vânt, presiune.        |
|  - F3. Prognoză extinsă pe 5 zile (intervale orare) cu minime și maxime zilnice.                  |
|  - F4. Gestiunea listei de orașe favorite (adăugare, eliminare, selecție rapidă cu 1-click).      |
|  - F5. Tratare robustă a erorilor de rețea (oraș negăsit, rețea offline, cheie API invalidă).     |
|                                                                                                    |
|  [SHOULD-HAVE / AVANSATE]                                                                         |
|  - F6. Persistență istorică locală în SQLite (salvare automată măsurători interogate).             |
|  - F7. Modul analitic cu Java Streams: medii, extreme climatice, tendințe barometrice.             |
|  - F8. Sistem automat de alerte vizuale (depășire praguri vânt, caniculă, îngheț).                 |
|  - F9. Export rapoarte prognoză în formate fișier CSV și JSON prin Java NIO.2.                    |
|  - F10. Caching inteligent în memorie cu TTL de 15 minute (protecție apeluri redundante).          |
|                                                                                                    |
|  [NICE-TO-HAVE / BONUSURI]                                                                        |
|  - F11. Comutare dinamică multi-provider (Open-Meteo fără cheie vs. OpenWeatherMap).              |
|  - F12. Grafic vizual dinamic al curbei de temperatură pe 24-48 ore (JavaFX Charts).               |
|  - F13. Mod de testare Offline MockWeatherService (generare date simulate fără internet).         |
|  - F14. Comutare temă vizuală interfață grafică (Dark Mode / Light Mode).                         |
+----------------------------------------------------------------------------------------------------+
```

---

### 5.3. Specificația Cerințelor Non-Funcționale

* **Performanță & Timp de Răspuns:** Timpul de răspuns al interfeței grafice la interacțiunile utilizatorului este sub 16 ms (60 cadre pe secundă). Pentru căutările aflate în cache, timpul de afișare este sub 5 ms.
* **Robusteză & Reziliență:** Aplicația nu se blochează (nu aruncă excepții necapturate în consolă) în cazul deconectării cablului de rețea, introducerii de caractere invalide sau căderii serverelor API externe.
* **Concurrency & Fir de Execuție:** Nicio operațiune de I/O (rețea sau disc) nu rulează pe Event Dispatch Thread (firul UI).
* **Portabilitate:** Proiectul se compilează și rulează pe orice mașină cu JDK 17 sau 21 fără a necesita instalarea de servere de baze de date sau unelte de configurare de sistem.
* **Securitate:** Interogările SQL sunt parametrizate exclusiv prin `PreparedStatement`, eliminând riscul de SQL Injection. Cheile de API sunt citite din fișiere de proprietăți externe, nefiind stocate "hardcoded" în codul sursă.

---

### 5.4. Arhitectura Tehnică & Structura Ierarhică a Pachetelor (`ro.univ.weather.*`)

Proiectul este structurat într-o ierarhie modulară conform celor mai bune practici de arhitectură software:

```
ro.univ.weather/
├── MainApp.java                       // Punctul de intrare în aplicația JavaFX / Swing
│
├── config/                            // Managementul configurației aplicației
│   ├── AppConfig.java                 // [Singleton] Citire app.properties, API keys
│   └── DatabaseConfig.java            // Căi conexiune JDBC SQLite locală
│
├── model/                             // Modele de domeniu și DTO-uri
│   ├── WeatherData.java               // Java Record: Date curente validate
│   ├── ForecastItem.java              // Java Record: Eșantion prognoză orară
│   ├── WeatherForecast.java           // Colecție de eșantioane prognoză
│   ├── WeatherCondition.java          // Enum condiții (CLEAR, RAIN, SNOW, STORM)
│   ├── WeatherAlert.java              // Entitate alertă (Comparable după severitate)
│   └── FavoriteCity.java              // Model oraș favorit
│
├── client/                            // Stratul de acces la rețea și API-uri
│   ├── WeatherApiClient.java          // [Interfață / Strategy] Contractul comun
│   ├── AbstractWeatherApiClient.java  // Clasă abstractă de bază (timeout-uri, HTTP)
│   ├── OpenMeteoClient.java           // Implementare fără cheie API (Zero Setup)
│   ├── OpenWeatherMapClient.java      // Implementare OpenWeather standard
│   ├── MockWeatherClient.java         // Furnizor mock pentru testare offline
│   ├── WeatherClientFactory.java      // [Factory Method] Instanțiere dinamică
│   └── CachedWeatherProxy.java        // [Decorator/Proxy] Cache in-memory cu TTL
│
├── adapter/                           // Transformare DTO extern în model intern
│   ├── OpenWeatherAdapter.java        // [Adapter] Parser JSON OpenWeather
│   └── OpenMeteoAdapter.java          // [Adapter] Parser JSON Open-Meteo
│
├── repository/                        // Persistență relațională și I/O
│   ├── WeatherRepository.java         // Interfață DAO pentru operațiuni baze de date
│   ├── JdbcWeatherRepository.java     // Implementare SQLite cu PreparedStatement
│   └── DatabaseConnectionManager.java // [Singleton] Administrare conexiune JDBC
│
├── export/                            // Export rapoarte în sistemul de fișiere
│   ├── WeatherExporter.java           // [Strategy] Interfață comună de export
│   ├── CsvWeatherExporter.java        // Export CSV prin Java NIO.2 Files/BufferedWriter
│   └── JsonWeatherExporter.java       // Export JSON structurat
│
├── service/                           // Logica de afaceri și orchestrare
│   ├── WeatherServiceFacade.java      // [Facade] Punct unificat de acces la servicii
│   ├── WeatherAnalyticsService.java   // Procesare avansată prin Java Streams
│   ├── WeatherAlertEngine.java        // Monitorizare praguri critice (Min-Heap)
│   └── WeatherPollerService.java      // ScheduledExecutorService pentru auto-refresh
│
├── event/                             // Evenimente asincrone [Observer Pattern]
│   ├── WeatherUpdateEvent.java        // Eveniment emis la descărcarea de date noi
│   ├── WeatherEventPublisher.java     // Subiectul care notifică observerii
│   └── WeatherObserver.java           // Interfață pentru ascultătorii de evenimente
│
├── exception/                         // Ierarhie customizată de excepții
│   ├── WeatherAppException.java       // Rădăcina verificată a excepțiilor
│   ├── WeatherApiException.java       // Erori la nivel de protocol sau server API
│   ├── CityNotFoundException.java     // HTTP 404 - Oraș inexistent
│   ├── ApiKeyInvalidException.java    // HTTP 401 - Cheie lipsă sau expirată
│   ├── NetworkException.java          // Erori de conexiune / socket timeout
│   ├── StorageException.java          // Erori JDBC sau I/O fișiere
│   └── InvalidMeasurementException.java// Erori de validare domenială (invarianți)
│
├── ui/                                // Nivelul de prezentare grafică
│   ├── controller/
│   │   ├── MainDashboardController.java // Controller principal MVC
│   │   ├── ForecastChartController.java // Controller grafic evoluție temperatură
│   │   └── FavoritesController.java     // Controller listă orașe favorite
│   ├── view/
│   │   ├── main_dashboard.fxml        // FXML interfață grafică
│   │   └── dark_theme.css             // Stilizare CSS3 modernă
│   └── component/
│       └── WeatherCardComponent.java  // Widget custom reprezentare vizuală vreme
│
└── util/                              // Clase utilitare
    ├── DateTimeUtil.java              // Formatare modernă cu java.time API
    └── ValidationUtil.java            // Verificare date de intrare utilizator
```

---

### 5.5. Diagrame ASCII de Arhitectură și Diagrame de Clase

#### Diagramă Conceptuală de Clase (Relații Cheie GoF & OOP):
```
    +──────────────────────────+                  +──────────────────────────+
    |   <<interface>>          |                  |   <<interface>>          |
    |   WeatherApiClient       |                  |   WeatherRepository      |
    +──────────────────────────+                  +──────────────────────────+
    | +fetchCurrent(city)      |                  | +saveSnapshot(report)    |
    | +fetchForecast(city)     |                  | +getHistory(city, limit) |
    +─────────────▲────────────+                  +─────────────▲────────────+
                  │                                             │
      ┌───────────┴───────────┐                     ┌───────────┴───────────┐
      │                       │                     │                       │
+─────┴────────────────+ +────┴───────────────+ +───┴─────────────────+ +───┴───────────────+
| OpenWeatherMapClient | |  OpenMeteoClient   | | JdbcWeatherRepo     | | InMemoryMockRepo  |
+──────────────────────+ +────────────────────+ +─────────────────────+ +───────────────────+
      │                                             │
      │ folosește                                   │ folosește
      ▼                                             ▼
+──────────────────────+                        +───────────────────────────────────────────+
|  OpenWeatherAdapter  |                        | DatabaseConnectionManager (SINGLETON)     |
+──────────────────────+                        +───────────────────────────────────────────+
      │ generează
      ▼
+───────────────────────────────────────────────────────────────────────────────────────────+
|                                    WeatherData (RECORD)                                   |
| - cityName: String                                                                        |
| - temperature: double                                                                     |
| - humidity: int                                                                           |
| - condition: WeatherCondition                                                             |
+─────────────────────────────────────────────▲─────────────────────────────────────────────+
                                              │ notifică date noi
                                              │
                               +──────────────┴──────────────+
                               |    WeatherEventPublisher    |  (OBSERVER SUBJECT)
                               +─────────────────────────────+
                               | +subscribe(WeatherObserver) |
                               | +notifyObservers(event)     |
                               +──────────────▲──────────────+
                                              │
                                              ▼
                               +─────────────────────────────+
                               |    <<interface>>            |
                               |    WeatherObserver          |
                               +─────────────────────────────+
                               | +onWeatherUpdated(event)    |
                               | +onError(exception)         |
                               +──────────────▲──────────────+
                                              │ implementează
                               ┌──────────────┴──────────────┐
                               │                             │
                 +─────────────┴────────────+  +─────────────┴────────────+
                 |  MainDashboardController |  |    WeatherAlertEngine    |
                 +──────────────────────────+  +──────────────────────────+
```

---

### 5.6. Planificarea pe Etape a Dezvoltării (WBS pe 8 Săptămâni)

Proiectul este dimensionat realist pentru a fi implementat pe parcursul a 8 săptămâni de lucru:

```
[Etapa 1: S1-S2] ──► [Etapa 2: S3-S4] ──► [Etapa 3: S5-S6] ──► [Etapa 4: S7-S8]
  Modelare OOP,        Persistență JDBC,     Interfață GUI,        Testare JUnit 5,
  Excepții & API       I/O & Concurrency     Observer & Streams    Polish & Documentație
```

* **Etapa 1 (Săptămânile 1–2): Fundația de Domeniu, Excepții & Client API de Bază**
  * *Activități:* Crearea ierarhiei de clase de domeniu folosind Java Records (`WeatherData`, `ForecastItem`, `Coordinates`). Implementarea ierarhiei custom de excepții (`WeatherAppException` și subclasele). Configurarea clientului HTTP `java.net.http.HttpClient` și implementarea adaptorului pentru `Open-Meteo` și a serviciului demonstrativ `MockWeatherClient`.
  * *Livrabil intermediar:* Test de consolă funcțional care descarcă și afișează datele meteorologice curente pentru București și Cluj-Napoca.
* **Etapa 2 (Săptămânile 3–4): Persistență Relațională (JDBC), I/O & Concurență**
  * *Activități:* Crearea bazei de date SQLite și a tabelelor DDL (`weather_history`, `favorite_cities`). Implementarea singleton-ului `DatabaseConnectionManager` și a clasei `JdbcWeatherRepository` cu `PreparedStatement` și tranzacții. Realizarea modulului de export rapoarte CSV prin Java NIO.2. Implementarea apelurilor asincrone cu `CompletableFuture` și a daemon-ului de reîmprospătare cu `ScheduledExecutorService`.
  * *Livrabil intermediar:* Mecanism complet de salvare și interogare din SQLite și export fișier CSV generat cu succes pe disc.
* **Etapa 3 (Săptămânile 5–6): Interfață Utilizator (JavaFX / Swing), Observer & Streams**
  * *Activități:* Construirea interfeței grafice (panou vreme curentă, selector favorite, tabel istoric, grafic de temperatură). Implementarea pattern-ului `Observer` pentru decuplarea evenimentelor de date de controalele vizuale. Sincronizarea corectă a firelor de execuție prin `Platform.runLater()`. Dezvoltarea modulului de procesare analitică `WeatherAnalyticsService` prin Streams API (`DoubleSummaryStatistics`, `groupingBy`).
  * *Livrabil intermediar:* Aplicație desktop complet funcțională, cu interfață grafică receptivă, căutare orașe și grafice de evoluție.
* **Etapa 4 (Săptămânile 7–8): Testare Automată (JUnit 5 / Mockito), Optimizare & Documentare**
  * *Activități:* Redactarea suitei de minimum 20 de teste unitare (JUnit 5 + Mockito). Optimizarea cache-ului concurent TTL. Documentarea completă a codului sursă prin comentarii Javadoc. Împachetarea aplicației ca arhivă JAR executabilă autonomă.
  * *Livrabil final:* Cod sursă complet, proiect Maven gata de compilare cu `mvn clean package`, raport de teste și manual de instalare.

---

### 5.7. Ghid de Instalare, Configurare și Rulare Rapidă pentru Evaluator

Proiectul este structurat pentru a garanta o experiență de evaluare impecabilă și fără efort pentru cadrul didactic:

#### Cerințe Minime de Sistem:
* Java Development Kit: **JDK 17** sau **JDK 21** instalat (`java -version`).
* Instrument de Build: **Apache Maven 3.8+** (opțional, proiectul include scriptul Maven Wrapper: `./mvnw` pe Linux/macOS sau `mvnw.cmd` pe Windows).

#### Comenzi de Rulare din Terminal:
```bash
# 1. Clonarea sau dezarhivarea proiectului și navigarea în director
cd "d:\AN 3 SEM 1\Proiect - JAVA"

# 2. Rularea suitei complete de teste unitare și generarea raportului
./mvnw clean test

# 3. Lansarea aplicației în execuție directă (folosind Open-Meteo - Fără cheie necesară!)
./mvnw javafx:run

# 4. (Opțional) Rularea în mod Mock Offline (simulare completă fără acces internet)
./mvnw javafx:run -Djavafx.args="--mock"

# 5. Generarea documentației academice complete Javadoc
./mvnw javadoc:javadoc
```

---

### 5.8. Grila Academică de Evaluare și Notare (10 Puncte cu Defalcare Riguroasă)

Această grilă reflectă standardele universitare aplicate în facultățile de elită de calculatoare și informatică, oferind o defalcare transparentă pe criterii de performanță:

| Criteriu de Evaluare | Punctaj Maxim | Punctaj Minim (Trecere) | Condiții de Acordare a Punctajului Maxim | Penalizări și Cauze de Depunctare |
|---|:---:|:---:|---|---|
| **1. Oficiu, Structura Proiectului & Rulare Autonomă** | **1.00 p** | 1.00 p | Proiectul se compilează direct din linie de comandă cu `mvn clean compile test` fără erori; respectă convențiile Java de denumire a pachetelor (`ro.univ.weather.*`). | Proiectul nu se compilează (-1.0p); biblioteci adăugate manual fără Maven (-0.5p); nerespectare convenții denumiri Java (-0.3p). |
| **2. Modelare OOP & Concepte Fundamentale** | **2.00 p** | 1.00 p | Ierarhie clară de clase, utilizare justificată a moștenirii (`AbstractWeatherApiClient`), polimorfism prin interfețe (`WeatherApiClient`, `WeatherRepository`), încapsulare riguroasă, folosire de Java Records. | Câmpuri `public` nemotivate (-0.5p); duplicare masivă de cod (-0.5p); lipsa polimorfismului / `instanceof` în lanț (-0.8p). |
| **3. Colecții Java & Streams API** | **1.50 p** | 0.75 p | Utilizarea justificată a cel puțin 3 tipuri distincte de colecții (`List`, `Set`, `Map`, `PriorityQueue`); procesări avansate de date meteorologice prin Streams (`groupingBy`, `filter`, `DoubleSummaryStatistics`). | Folosirea exclusivă a `ArrayList` pentru orice (-0.5p); bucle clasice `for` imperative în loc de operații Streams cerute (-0.5p). |
| **4. Tratarea Excepțiilor & Persistență (I/O & JDBC)** | **1.50 p** | 0.75 p | Ierarhie customizată de excepții (minim 3 clase); folosire consecventă a mecanismului `try-with-resources`; persistență funcțională în SQLite cu `PreparedStatement` și tranzacții; export fișiere NIO.2. | Blocuri `catch` goale (-0.5p); SQL Injection prin concatenare de stringuri (-1.0p); conexiuni sau fișiere neînchise (-0.5p). |
| **5. Concurrency, Multithreading & Asincronism** | **1.50 p** | 0.75 p | Apelurile API și I/O-ul de disc rulează pe worker threads prin `CompletableFuture`; interfața grafică nu îngheață niciodată; sincronizare sigură pe datele partajate prin `ConcurrentHashMap` și `Platform.runLater()`. | Blocarea firului UI în timpul apelurilor de rețea (-1.0p); race conditions neprotejate pe starea partajată (-0.5p). |
| **6. Șabloane de Proiectare Software (Design Patterns)** | **1.50 p** | 0.75 p | Implementarea corectă a cel puțin 4 șabloane: Creational (Singleton, Factory Method, Builder), Structural (Adapter, Facade) și Behavioral (Observer, Strategy). | Șabloane implementate doar formal, neintegrate în fluxul aplicației (-0.5p per șablon); Singleton nesigur la concurență (-0.3p). |
| **7. Calitatea Codului, Testare Unitară & Documentare** | **1.00 p** | 0.50 p | Minim 15-20 de teste unitare relevante (JUnit 5 + Mockito) cu aserțiuni clare; comentarii Javadoc pe toate clasele și metodele publice; cod lizibil și curat. | Lipsă completă a testelor unitare (-0.8p); teste triviale fără aserțiuni reale (-0.5p); lipsa comentariilor Javadoc (-0.3p). |
| **TOTAL GENERAL** | **10.00 p** | **5.50 p** | **Toate cerințele satisfăcute la standarde de excelență academică universitară.** | *Pentru promovare este necesară obținerea a minim 5.00 puncte finale.* |

---

### 5.9. Formular Formal de Aprobare și Semnătură

Prezenta propunere a fost analizată și aprobată de către cadrul didactic coordonator pentru a servi drept temă oficială de proiect semestrial:

```
+----------------------------------------------------------------------------------------------------+
|                                FORMULAR OFICIAL DE VALIDARE A TEMEI                                |
+----------------------------------------------------------------------------------------------------+
|                                                                                                    |
|  Decizie Cadru Didactic:      [   ] ADMIS / APROBAT fără rezerve                                   |
|                               [   ] ADMIS CU CONDIȚII (se vor menționa cerințele suplimentare)     |
|                               [   ] RESPINS (se solicită reformularea temei)                       |
|                                                                                                    |
|  Varianta Arhitecturală Aleasă: [ X ] Varianta 1 (Desktop Pure Java - JavaFX / Swing cu FlatLaf)   |
|                                 [   ] Varianta 2 (Web Client-Server - Spring Boot 3.x)             |
|                                 [   ] Varianta 3 (Data Daemon - Java Core + Raw JDBC + IPC)        |
|                                                                                                    |
|  Observații / Recomandări Didactice: ............................................................. |
|  ................................................................................................. |
|                                                                                                    |
|  Semnătură Cadru Didactic Coordonator:                       Semnătură Student Proponent:          |
|  .....................................                       ............................          |
|  Data: ..... / ..... / 2026                                  Data: ..... / ..... / 2026            |
+----------------------------------------------------------------------------------------------------+
```

---

# 6. Bibliografie și Resurse Tehnice de Referință

1. **Bloch, Joshua.** *Effective Java (3rd Edition)*. Addison-Wesley Professional, 2018. (Ghidul de bune practici pentru clase imutabile, constructori cu Builder, equals/hashCode și ierarhii de excepții).
2. **Gamma, Erich, Richard Helm, Ralph Johnson, John Vlissides.** *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley, 1994. (Lucrarea de referință GoF pentru șabloanele Singleton, Factory, Observer, Adapter, Facade, Strategy).
3. **Goetz, Brian, Tim Peierls, Joshua Bloch, Joseph Bowbeer, David Holmes, Doug Lea.** *Java Concurrency in Practice*. Addison-Wesley Professional, 2006. (Concepte de thread safety, sincronizare, executor services și colecții concurente).
4. **Oracle Corporation.** *Java Platform, Standard Edition Documentation (JDK 17 / JDK 21)*. https://docs.oracle.com/en/java/javase/
5. **OpenJFX Community.** *JavaFX Documentation & Scene Builder User Guide*. https://openjfx.io/
6. **Open-Meteo.** *Free Weather API Documentation (No API Key Required)*. https://open-meteo.com/en/docs
7. **OpenWeatherMap.** *One Call API 3.0 & Weather Data Documentation*. https://openweathermap.org/api
8. **JUnit Team.** *JUnit 5 User Guide & Architecture*. https://junit.org/junit5/docs/current/user-guide/
9. **FormDev Software GmbH.** *FlatLaf — Clean and Modern Look and Feel for Java Swing*. https://www.formdev.com/flatlaf/
