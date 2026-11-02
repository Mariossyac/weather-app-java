package ro.univ.weather.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Reprezinta o locatie geografica (oras, tara, coordonate GPS).
 */
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final String country;
    private final String adminRegion;
    private final double latitude;
    private final double longitude;

    public Location(String name, String country, String adminRegion, double latitude, double longitude) {
        this.name = Objects.requireNonNull(name, "Numele orasului nu poate fi null");
        this.country = country != null ? country : "";
        this.adminRegion = adminRegion != null ? adminRegion : "";
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getAdminRegion() {
        return adminRegion;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * Returneaza o eticheta usor de citit (ex: "București, România").
     */
    public String getDisplayName() {
        StringBuilder sb = new StringBuilder(name);
        if (!adminRegion.isEmpty() && !adminRegion.equalsIgnoreCase(name)) {
            sb.append(" (").append(adminRegion).append(")");
        }
        if (!country.isEmpty()) {
            sb.append(", ").append(country);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0 &&
               Double.compare(location.longitude, longitude) == 0 &&
               Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude);
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}

