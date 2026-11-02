package ro.univ.weather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ro.univ.weather.model.Location;
import ro.univ.weather.storage.FavoritesStorage;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FavoritesStorageTest {

    @Test
    void testFavoritesStorageInitializationAndAddRemove(@TempDir Path tempDir) {
        File tempFile = tempDir.resolve("test-favorites.json").toFile();
        FavoritesStorage storage = new FavoritesStorage(tempFile);

        // Ar trebui sa se populeze cu orase implicite la inceput
        List<Location> initialFavorites = storage.getFavorites();
        assertFalse(initialFavorites.isEmpty());
        assertTrue(storage.isFavorite(new Location("București", "România", "București", 44.4323, 26.1063)));

        // Adaugam un oras nou (ex: Sibiu)
        Location sibiu = new Location("Sibiu", "România", "Sibiu", 45.7983, 24.1256);
        assertFalse(storage.isFavorite(sibiu));

        boolean added = storage.addFavorite(sibiu);
        assertTrue(added);
        assertTrue(storage.isFavorite(sibiu));

        // Testam reincarcarea dintr-o noua instanta pe acelasi fisier
        FavoritesStorage reloadedStorage = new FavoritesStorage(tempFile);
        assertTrue(reloadedStorage.isFavorite(sibiu));

        // Stergem orasul
        boolean removed = storage.removeFavorite(sibiu);
        assertTrue(removed);
        assertFalse(storage.isFavorite(sibiu));
    }
}

