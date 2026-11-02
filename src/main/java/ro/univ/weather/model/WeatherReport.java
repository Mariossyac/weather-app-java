package ro.univ.weather.model;

import java.util.Collections;
import java.util.List;

/**
 * Raportul complet meteo care reuneste locatia, datele curente si prognoza zilnica.
 */
public class WeatherReport {
    private final Location location;
    private final CurrentWeather current;
    private final List<DailyForecast> dailyForecasts;

    public WeatherReport(Location location, CurrentWeather current, List<DailyForecast> dailyForecasts) {
        this.location = location;
        this.current = current;
        this.dailyForecasts = dailyForecasts != null ? List.copyOf(dailyForecasts) : Collections.emptyList();
    }

    public Location getLocation() {
        return location;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }
}

