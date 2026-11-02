package ro.univ.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ro.univ.weather.model.CurrentWeather;
import ro.univ.weather.model.DailyForecast;
import ro.univ.weather.model.Location;
import ro.univ.weather.model.WeatherReport;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

/**
 * Serviciu asincron pentru interogarea API-ului meteorologic public Open-Meteo.
 * Foloseste HttpClient nativ (Java 11+) si Jackson pentru parsarea JSON.
 */
public class WeatherApiService {

    private static final String GEOCODING_URL = "https://geocoding-api.open-meteo.com/v1/search";
    private static final String FORECAST_URL = "https://api.open-meteo.com/v1/forecast";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public WeatherApiService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Cauta locatii dupa nume (asincron).
     *
     * @param cityName Numele orasului introdus de utilizator
     * @return O lista de locatii gasite
     */
    public CompletableFuture<List<Location>> searchLocationsAsync(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            return CompletableFuture.completedFuture(Collections.emptyList());
        }

        String encodedName = URLEncoder.encode(cityName.trim(), StandardCharsets.UTF_8);
        String url = String.format("%s?name=%s&count=6&language=ro&format=json", GEOCODING_URL, encodedName);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .header("Accept", "application/json")
                .GET()
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::parseLocationsJson);
    }

    /**
     * Obtine datele meteo curente si prognoza pentru o locatie (asincron).
     *
     * @param location Locatia pentru care se doreste prognoza
     * @return Raportul meteo complet
     */
    public CompletableFuture<WeatherReport> fetchWeatherAsync(Location location) {
        if (location == null) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Locatia nu poate fi null"));
        }

        // Folosim Locale.US pentru ca formatarea coordonatelor sa fie cu punct (ex: 44.432, nu 44,432)
        String url = String.format(Locale.US,
                "%s?latitude=%.4f&longitude=%.4f&current=temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,wind_speed_10m&daily=weather_code,temperature_2m_max,temperature_2m_min&timezone=auto",
                FORECAST_URL, location.getLatitude(), location.getLongitude());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .header("Accept", "application/json")
                .GET()
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> parseWeatherReportJson(location, json));
    }

    private List<Location> parseLocationsJson(String json) {
        List<Location> locations = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode results = root.get("results");
            if (results != null && results.isArray()) {
                for (JsonNode item : results) {
                    String name = item.path("name").asText();
                    String country = item.path("country").asText("");
                    String admin1 = item.path("admin1").asText("");
                    double lat = item.path("latitude").asDouble();
                    double lon = item.path("longitude").asDouble();

                    locations.add(new Location(name, country, admin1, lat, lon));
                }
            }
        } catch (Exception e) {
            System.err.println("Eroare la parsarea locatiilor: " + e.getMessage());
        }
        return locations;
    }

    private WeatherReport parseWeatherReportJson(Location location, String json) {
        try {
            JsonNode root = objectMapper.readTree(json);

            // 1. Parsare vreme curenta
            JsonNode current = root.path("current");
            double temp = current.path("temperature_2m").asDouble();
            double apparentTemp = current.path("apparent_temperature").asDouble();
            int humidity = current.path("relative_humidity_2m").asInt();
            double windSpeed = current.path("wind_speed_10m").asDouble();
            int weatherCode = current.path("weather_code").asInt();
            String time = current.path("time").asText();

            var weatherInfo = WeatherCodeMapper.getInfo(weatherCode);
            CurrentWeather currentWeather = new CurrentWeather(
                    temp, apparentTemp, humidity, windSpeed, weatherCode,
                    weatherInfo.description(), weatherInfo.icon(), time
            );

            // 2. Parsare prognoza zilnica pe 7 zile
            JsonNode daily = root.path("daily");
            JsonNode dates = daily.path("time");
            JsonNode codes = daily.path("weather_code");
            JsonNode maxTemps = daily.path("temperature_2m_max");
            JsonNode minTemps = daily.path("temperature_2m_min");

            List<DailyForecast> forecasts = new ArrayList<>();
            if (dates.isArray()) {
                for (int i = 0; i < dates.size(); i++) {
                    LocalDate date = LocalDate.parse(dates.get(i).asText());
                    int dayCode = codes.get(i).asInt();
                    double maxT = maxTemps.get(i).asDouble();
                    double minT = minTemps.get(i).asDouble();
                    var dayInfo = WeatherCodeMapper.getInfo(dayCode);

                    forecasts.add(new DailyForecast(date, minT, maxT, dayCode, dayInfo.description(), dayInfo.icon()));
                }
            }

            return new WeatherReport(location, currentWeather, forecasts);
        } catch (Exception e) {
            throw new RuntimeException("Eroare la parsarea raportului meteo: " + e.getMessage(), e);
        }
    }
}

