package ro.univ.weather.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ro.univ.weather.model.Location;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestioneaza persistenta listei de orase favorite ale utilizatorului intr-un fisier local JSON.
 * Este thread-safe si ofera orase implicite la prima initializare a aplicatiei.
 */
public class FavoritesStorage {

    private static final String DEFAULT_FILE_NAME = "favorites.json";
    private final File storageFile;
    private final ObjectMapper objectMapper;
    private final List<Location> cachedFavorites;

    public FavoritesStorage() {
        this(new File(DEFAULT_FILE_NAME));
    }

    public FavoritesStorage(File storageFile) {
        this.storageFile = storageFile;
        this.objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        this.cachedFavorites = new ArrayList<>();
        loadFromDisk();
    }

    /**
     * Incarca favoritele de pe disc sau populeaza cu orase implicite daca fisierul lipseste.
     */
    private synchronized void loadFromDisk() {
        cachedFavorites.clear();
        if (storageFile.exists() && storageFile.length() > 0) {
            try {
                List<Location> list = objectMapper.readValue(storageFile, new TypeReference<List<Location>>() {});
                if (list != null) {
                    cachedFavorites.addAll(list);
                    return;
                }
            } catch (IOException e) {
                System.err.println("Avertisment: Nu s-a putut citi fisierul de favorite (" + e.getMessage() + "). Se vor folosi valorile implicite.");
            }
        }

        // Valori implicite daca fisierul nu exista
        cachedFavorites.addAll(getDefaultLocations());
        saveToDisk();
    }

    /**
     * Salveaza lista curenta de favorite pe disc.
     */
    private synchronized void saveToDisk() {
        try {
            objectMapper.writeValue(storageFile, cachedFavorites);
        } catch (IOException e) {
            System.err.println("Eroare la salvarea fisierului de favorite: " + e.getMessage());
        }
    }

    /**
     * Returneaza o copie a listei curente de favorite.
     */
    public synchronized List<Location> getFavorites() {
        return Collections.unmodifiableList(new ArrayList<>(cachedFavorites));
    }

    /**
     * Adauga o noua locatie la favorite (fara duplicate).
     */
    public synchronized boolean addFavorite(Location location) {
        if (location == null || isFavorite(location)) {
            return false;
        }
        cachedFavorites.add(location);
        saveToDisk();
        return true;
    }

    /**
     * Sterge o locatie din favorite.
     */
    public synchronized boolean removeFavorite(Location location) {
        if (location == null) {
            return false;
        }
        boolean removed = cachedFavorites.removeIf(fav -> fav.equals(location) || 
                (fav.getName().equalsIgnoreCase(location.getName()) && 
                 Double.compare(fav.getLatitude(), location.getLatitude()) == 0));
        if (removed) {
            saveToDisk();
        }
        return removed;
    }

    /**
     * Verifica daca o locatie este deja salvata la favorite.
     */
    public synchronized boolean isFavorite(Location location) {
        if (location == null) {
            return false;
        }
        return cachedFavorites.stream().anyMatch(fav -> 
                fav.equals(location) || 
                (fav.getName().equalsIgnoreCase(location.getName()) && 
                 Math.abs(fav.getLatitude() - location.getLatitude()) < 0.01 && 
                 Math.abs(fav.getLongitude() - location.getLongitude()) < 0.01));
    }

    /**
     * Orase implicite de start pentru utilizator.
     */
    public static List<Location> getDefaultLocations() {
        return List.of(
                new Location("București", "România", "București", 44.4323, 26.1063),
                new Location("Cluj-Napoca", "România", "Cluj", 46.7712, 23.6236),
                new Location("Timișoara", "România", "Timiș", 45.7537, 21.2257),
                new Location("Iași", "România", "Iași", 47.1585, 27.6014),
                new Location("Brașov", "România", "Brașov", 45.6579, 25.6012),
                new Location("Constanța", "România", "Constanța", 44.1807, 28.6343)
        );
    }
}

