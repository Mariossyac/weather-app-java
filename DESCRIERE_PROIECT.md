# Descriere Proiect: Aplicație Meteo (Java)

## 1. Prezentare Generală
Proiectul constă într-o aplicație meteorologică interactivă (**WeatherApp**) care preia date în timp real de la un API meteo public (ex. Open-Meteo / OpenWeatherMap) și oferă utilizatorului:
* Vremea curentă (temperatură, umiditate, vânt, presiune, descriere meteo);
* Prognoză pe următoarele 5-7 zile;
* Căutare după orașe și salvarea locațiilor favorite;
* Istoric al căutărilor și export de date (rapoarte CSV/JSON).

---

## 2. Variante de Tehnologii (Alege una dintre ele)

### Varianta A: Aplicație Desktop (Recomandată pentru un curs clasic de Java)
* **Limbaj:** Java (17 sau 21)
* **Interfață Grafică:** JavaFX sau Swing (interfață modernă pentru utilizator)
* **Bază de date / Persistență:** SQLite sau fișiere locale (JSON/CSV) pentru orașe favorite și istoric
* **Comunice Rețea:** `HttpClient` nativ Java pentru interogarea API-ului meteo
* **Format date:** JSON (procesat cu Jackson sau Gson)

---

### Varianta B: Aplicație Web (Backend Java + Frontend Web)
* **Backend:** Java cu **Spring Boot** (creează un API REST care preia și procesează datele meteo)
* **Frontend:** **HTML5, CSS3, JavaScript** (sau un framework modern precum React)
* **Bază de date:** MySQL sau H2 Database (stocare utilizatori, alerte, favorite)
* **Comunicare:** REST API între Frontend și Backend

---

### Varianta C: Aplicație Hibridă (Java + Python pentru Analiză)
* **Core & Multithreading:** Java (gestionează fluxul de date, interogarea periodică a vremii și salvarea în DB)
* **Modul de Analiză & Grafice:** Python (folosește librării precum Matplotlib / Pandas pentru generarea de diagrame cu evoluția vremii)
* **Bază de date:** PostgreSQL / SQLite pentru partajarea datelor între Java și Python

---

## 3. Concepte Java demonstrate în proiect
Proiectul acoperă cerințele curriculare de bază:
1. **Programare Orientată pe Obiecte (OOP):** Clase și obiecte (oraș, măsurătoare meteo, prognoză), încapsulare, moștenire, interfețe.
2. **Colecții Java:** `List`, `Map`, `Set` pentru stocarea prognozelor, cache-ului și a favoritelor.
3. **Multithreading / Concurență:** Apeluri asincrone la API pentru a nu bloca interfața utilizatorului (`CompletableFuture` sau fire de execuție separate).
4. **I/O și Tratarea Excepțiilor:** Conexiuni HTTP, parsare JSON, citire/scriere din fișiere, managementul erorilor de rețea.
5. **Design Patterns:** MVC (Model-View-Controller), Singleton (conexiune DB/serviciu API), Observer.

---

## 4. Text propus pentru trimis profesorului (Email / Formular)

> **Subiect:** Propunere temă proiect Java: Aplicație de Monitorizare Meteo
>
> **Bună ziua,**
> 
> Numele meu este [Numele Tău], student în anul [Anul], grupa [Grupa]. 
> Pentru proiectul la disciplina de Programare Java, doresc să propun dezvoltarea aplicației **WeatherApp**.
> 
> **Descrierea proiectului:**
> Aplicația permite vizualizarea vremii în timp real și a prognozei pe 5 zile pentru diverse orașe, preluând date dintr-un API public (Open-Meteo). Include funcționalități de căutare, salvare a orașelor favorite și istoric local.
> 
> **Tehnologii utilizate:**
> - **Java (Backend / Core):** Implementare OOP, Colecții, Fire de execuție (apeluri asincrone HTTP) și parsare JSON.
> - **UI:** [Alege: JavaFX / Swing SAU Web cu HTML/CSS/JS].
> - **Bază de date / Stocare:** [Alege: SQLite / fișiere locale JSON SAU MySQL/H2].
> - **API extern:** Open-Meteo REST API.
> 
> Vă rog să-mi comunicați dacă tema este acceptată sau dacă sunt necesare cerințe suplimentare.
> 
> Vă mulțumesc,  
> [Numele Tău]

