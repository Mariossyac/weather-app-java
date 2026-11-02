package ro.univ.weather.service;

/**
 * Traduce codurile meteorologice standard WMO (World Meteorological Organization)
 * in descrieri usor de inteles in limba romana si pictograme emoji corespunzatoare.
 */
public final class WeatherCodeMapper {

    private WeatherCodeMapper() {
        // Clasa utilitara
    }

    public static record WeatherInfo(String description, String icon) {}

    public static WeatherInfo getInfo(int code) {
        return switch (code) {
            case 0 -> new WeatherInfo("Cer senin", "☀️");
            case 1 -> new WeatherInfo("Predominant senin", "🌤️");
            case 2 -> new WeatherInfo("Parțial înnorat", "⛅");
            case 3 -> new WeatherInfo("Înnorat", "☁️");
            case 45 -> new WeatherInfo("Ceață", "🌫️");
            case 48 -> new WeatherInfo("Ceață cu depunere de chiciură", "🌫️");
            case 51 -> new WeatherInfo("Burniță ușoară", "🌦️");
            case 53 -> new WeatherInfo("Burniță moderată", "🌦️");
            case 55 -> new WeatherInfo("Burniță densă", "🌧️");
            case 56, 57 -> new WeatherInfo("Burniță înghețată", "🌧️❄️");
            case 61 -> new WeatherInfo("Ploaie ușoară", "🌦️");
            case 63 -> new WeatherInfo("Ploaie moderată", "🌧️");
            case 65 -> new WeatherInfo("Ploaie torențială", "🌧️");
            case 66, 67 -> new WeatherInfo("Ploaie înghețată", "🌧️❄️");
            case 71 -> new WeatherInfo("Ninsoare slabă", "🌨️");
            case 73 -> new WeatherInfo("Ninsoare moderată", "❄️");
            case 75 -> new WeatherInfo("Ninsoare abundentă", "❄️");
            case 77 -> new WeatherInfo("Grindină măruntă", "🌨️");
            case 80, 81 -> new WeatherInfo("Averse de ploaie", "🌧️");
            case 82 -> new WeatherInfo("Averse violente de ploaie", "⛈️");
            case 85, 86 -> new WeatherInfo("Averse de ninsoare", "🌨️❄️");
            case 95 -> new WeatherInfo("Furtună cu descărcări electrice", "⛈️");
            case 96, 99 -> new WeatherInfo("Furtună cu grindină", "⛈️");
            default -> new WeatherInfo("Condiții variabile", "🌤️");
        };
    }
}

