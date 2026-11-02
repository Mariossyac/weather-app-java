package ro.univ.weather.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Reprezinta prognoza meteo pentru o singura zi.
 */
public class DailyForecast {
    private final LocalDate date;
    private final double minTemperature;
    private final double maxTemperature;
    private final int precipitationProbability;
    private final int weatherCode;
    private final String description;
    private final String iconEmoji;

    public DailyForecast(LocalDate date, double minTemperature, double maxTemperature, 
                         int precipitationProbability, int weatherCode, 
                         String description, String iconEmoji) {
        this.date = date;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.precipitationProbability = precipitationProbability;
        this.weatherCode = weatherCode;
        this.description = description;
        this.iconEmoji = iconEmoji;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public int getPrecipitationProbability() {
        return precipitationProbability;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public String getDescription() {
        return description;
    }

    public String getIconEmoji() {
        return iconEmoji;
    }

    public String getDayOfWeekFormatted() {
        Locale roLocale = Locale.forLanguageTag("ro-RO");
        String day = date.format(DateTimeFormatter.ofPattern("EEEE", roLocale));
        return Character.toUpperCase(day.charAt(0)) + day.substring(1);
    }

    public String getShortDateFormatted() {
        Locale roLocale = Locale.forLanguageTag("ro-RO");
        return date.format(DateTimeFormatter.ofPattern("d MMM", roLocale));
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %s %.1f°C / %.1f°C - %s (Precipitații: %d%%)",
                getDayOfWeekFormatted(), getShortDateFormatted(), iconEmoji, 
                minTemperature, maxTemperature, description, precipitationProbability);
    }
}
