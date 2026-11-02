package ro.univ.weather.ui;

import ro.univ.weather.model.CurrentWeather;
import ro.univ.weather.model.Location;
import ro.univ.weather.model.WeatherReport;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Card grafic care afiseaza detaliile vremii curente pentru locatia selectata.
 */
public class CurrentWeatherPanel extends JPanel {

    private final JLabel cityLabel;
    private final JLabel iconLabel;
    private final JLabel conditionLabel;
    private final JLabel tempLabel;
    private final JLabel feelsLikeLabel;
    private final JLabel humidityLabel;
    private final JLabel windLabel;
    private final JLabel precipLabel;
    private final JLabel updateTimeLabel;

    public CurrentWeatherPanel() {
        setLayout(new BorderLayout(10, 15));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                new EmptyBorder(20, 25, 20, 25)
        ));
        setBackground(new Color(248, 250, 252));

        // 1. Antet: Oras si regiune
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        cityLabel = new JLabel("Selectează un oraș");
        cityLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        cityLabel.setForeground(new Color(30, 41, 59));

        updateTimeLabel = new JLabel("");
        updateTimeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        updateTimeLabel.setForeground(new Color(100, 116, 139));

        topPanel.add(cityLabel, BorderLayout.WEST);
        topPanel.add(updateTimeLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // 2. Zona centrala: Temperatura si Starea vremii
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 18, 10));
        centerPanel.setOpaque(false);

        tempLabel = new JLabel("-- °C");
        tempLabel.setFont(new Font("Segoe UI", Font.BOLD, 46));
        tempLabel.setForeground(new Color(15, 23, 42));

        // Label dedicat pentru iconita meteo cu font specific de emoji
        iconLabel = new JLabel("");
        iconLabel.setFont(getEmojiFont(38));

        conditionLabel = new JLabel("");
        conditionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        conditionLabel.setForeground(new Color(51, 65, 85));

        centerPanel.add(tempLabel);
        centerPanel.add(iconLabel);
        centerPanel.add(conditionLabel);
        add(centerPanel, BorderLayout.CENTER);

        // 3. Zona inferioara: 4 indicatori secundari (Resimtit, Umiditate, Vant, Precipitatii)
        JPanel detailsPanel = new JPanel(new GridLayout(1, 4, 12, 0));
        detailsPanel.setOpaque(false);
        detailsPanel.setBorder(new EmptyBorder(12, 0, 0, 0));

        feelsLikeLabel = createDetailLabel("Resimțit: -- °C");
        humidityLabel = createDetailLabel("Umiditate: -- %");
        windLabel = createDetailLabel("Vânt: -- km/h");
        precipLabel = createDetailLabel("Precipitații: -- %");

        detailsPanel.add(feelsLikeLabel);
        detailsPanel.add(humidityLabel);
        detailsPanel.add(windLabel);
        detailsPanel.add(precipLabel);
        add(detailsPanel, BorderLayout.SOUTH);
    }

    private JLabel createDetailLabel(String initialText) {
        JLabel label = new JLabel(initialText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(new Color(71, 85, 105));
        return label;
    }

    private Font getEmojiFont(int size) {
        Font font = new Font("Segoe UI Emoji", Font.PLAIN, size);
        if (!font.getFamily().equalsIgnoreCase("Segoe UI Emoji")) {
            font = new Font("Segoe UI Symbol", Font.PLAIN, size);
        }
        return font;
    }

    /**
     * Actualizeaza interfata grafica cu informatiile din raportul meteo.
     */
    public void updateData(WeatherReport report) {
        if (report == null) return;

        Location location = report.getLocation();
        CurrentWeather current = report.getCurrent();

        cityLabel.setText(location.getDisplayName());
        tempLabel.setText(String.format(java.util.Locale.US, "%.1f °C", current.getTemperature()));
        iconLabel.setText(current.getIconEmoji());
        conditionLabel.setText(current.getDescription());

        feelsLikeLabel.setText(String.format(java.util.Locale.US, "Resimțit: %.1f °C", current.getApparentTemperature()));
        humidityLabel.setText(String.format("Umiditate: %d %%", current.getHumidity()));
        windLabel.setText(String.format(java.util.Locale.US, "Vânt: %.1f km/h", current.getWindSpeed()));

        if (current.getPrecipitation() > 0) {
            precipLabel.setText(String.format(java.util.Locale.US, "Precipitații: %d %% (%.1f mm)", 
                    current.getPrecipitationProbability(), current.getPrecipitation()));
        } else {
            precipLabel.setText(String.format("Precipitații: %d %%", current.getPrecipitationProbability()));
        }

        if (current.getTime() != null && current.getTime().contains("T")) {
            String timePart = current.getTime().substring(current.getTime().indexOf("T") + 1);
            updateTimeLabel.setText("Ora locală: " + timePart);
        } else {
            updateTimeLabel.setText("");
        }
    }
}
