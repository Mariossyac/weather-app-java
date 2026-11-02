# WeatherApp — Aplicație Meteo Desktop în Java

O aplicație desktop modernă și intuitivă pentru vizualizarea stării vremii și a prognozei meteo, dezvoltată în Java pentru cursul universitar de programare.

## 🚀 Funcționalități principale
* **Vremea în timp real:** Temperatură curentă, descriere condiții (senin, ploaie etc.), umiditate, viteza vântului și presiune atmosferică.
* **Prognoză pe 5–7 zile:** Temperaturi maxime/minime și evoluție zilnică.
* **Căutare & Salvare Favorite:** Posibilitatea de a căuta orice locație și de a salva orașele preferate pentru acces rapid.
* **Stocare locală:** Istoric al căutărilor și lista de favorite salvate local.
* **Interfață intuitivă:** GUI desktop modern realizat în Java (JavaFX / Swing).

## 🛠️ Tehnologii utilizate
* **Limbaj:** Java (17+)
* **Interfață Grafică:** JavaFX / Swing
* **Consum API:** `java.net.http.HttpClient` pentru interogarea asincronă a API-ului [Open-Meteo](https://open-meteo.com/) (nu necesită cheie API plătită)
* **Parsare Date:** Jackson / Gson pentru procesarea răspunsurilor JSON
* **Persistență:** SQLite / JSON pentru salvarea orașelor favorite și setărilor

## 📋 Concepte Java demonstrate
* **OOP:** Încapsulare, moștenire, polimorfism, abstractizare (modele de date clare pentru măsurători, locații, prognoze).
* **Colecții Java:** `List`, `Map`, `Set` pentru gestionarea prognozelor și a istoricului.
* **Concurență / Multithreading:** Apeluri asincrone la API pentru o interfață fluidă și fără blocaje (`CompletableFuture`).
* **Design Patterns:** MVC (Model-View-Controller), Singleton (servicii API/stocare), Observer.
