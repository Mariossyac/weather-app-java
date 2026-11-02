package ro.univ.weather.model;

/**
 * Măsurătorile curente ale vremii pentru o locație.
 */
public class CurrentWeather {
    private final double temperature;
    private final double apparentTemperature;
    private final int humidity;
    private final double windSpeed;
    private final double precipitation;
    private final int precipitationProbability;
    private final int weatherCode;
    private final String description;
    private final String iconEmoji;
    private final String time;

    public CurrentWeather(double temperature, double apparentTemperature, int humidity, 
                          double windSpeed, double precipitation, int precipitationProbability,
                          int weatherCode, String description, String iconEmoji, String time) {
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.precipitation = precipitation;
        this.precipitationProbability = precipitationProbability;
        this.weatherCode = weatherCode;
        this.description = description;
        this.iconEmoji = iconEmoji;
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getPrecipitation() {
        return precipitation;
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

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%s %.1f°C (%s), Resimțit: %.1f°C, Umiditate: %d%%, Vânt: %.1f km/h, Precipitații: %d%%",
                iconEmoji, temperature, description, apparentTemperature, humidity, windSpeed, precipitationProbability);
    }
}
