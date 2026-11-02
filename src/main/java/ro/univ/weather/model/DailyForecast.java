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
    private final int weatherCode;
    private final String description;
    private final String iconEmoji;

    public DailyForecast(LocalDate date, double minTemperature, double maxTemperature, 
                         int weatherCode, String description, String iconEmoji) {
        this.date = date;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
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

    public int getWeatherCode() {
        return weatherCode;
    }

    public String getDescription() {
        return description;
    }

    public String getIconEmoji() {
        return iconEmoji;
    }

    /**
     * Numele zilei din saptamana formatat in romana (ex: "Luni", "Marți").
     */
    public String getDayOfWeekFormatted() {
        Locale roLocale = Locale.forLanguageTag("ro-RO");
        String day = date.format(DateTimeFormatter.ofPattern("EEEE", roLocale));
        return Character.toUpperCase(day.charAt(0)) + day.substring(1);
    }

    /**
     * Data formatata scurt (ex: "15 Oct").
     */
    public String getShortDateFormatted() {
        Locale roLocale = Locale.forLanguageTag("ro-RO");
        return date.format(DateTimeFormatter.ofPattern("d MMM", roLocale));
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %s %.1f°C / %.1f°C - %s",
                getDayOfWeekFormatted(), getShortDateFormatted(), iconEmoji, minTemperature, maxTemperature, description);
    }
}

