package ro.univ.weather;

import org.junit.jupiter.api.Test;
import ro.univ.weather.model.Location;
import ro.univ.weather.service.WeatherCodeMapper;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTest {

    @Test
    void testLocationCreationAndDisplay() {
        Location loc = new Location("București", "România", "București", 44.4323, 26.1063);
        assertEquals("București", loc.getName());
        assertEquals("România", loc.getCountry());
        assertTrue(loc.getDisplayName().contains("București"));
        assertTrue(loc.getDisplayName().contains("România"));
    }

    @Test
    void testWeatherCodeMapperClearSky() {
        var info = WeatherCodeMapper.getInfo(0);
        assertNotNull(info);
        assertEquals("Cer senin", info.description());
        assertEquals("☀️", info.icon());
    }

    @Test
    void testWeatherCodeMapperRain() {
        var info = WeatherCodeMapper.getInfo(63);
        assertNotNull(info);
        assertTrue(info.description().toLowerCase().contains("ploaie"));
    }
}

