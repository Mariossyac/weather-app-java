package ro.univ.weather.ui;

import ro.univ.weather.model.Location;
import ro.univ.weather.model.WeatherReport;
import ro.univ.weather.service.WeatherApiService;
import ro.univ.weather.storage.FavoritesStorage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Fereastra principala a aplicatiei WeatherApp.
 * Asambleaza bara de cautare, selectia de favorite si panourile de prognoza.
 */
public class MainFrame extends JFrame {

    private final WeatherApiService apiService;
    private final FavoritesStorage favoritesStorage;

    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<Location> favoritesComboBox;
    private JButton favoriteToggleButton;
    private JLabel statusLabel;

    private CurrentWeatherPanel currentWeatherPanel;
    private ForecastPanel forecastPanel;

    private Location currentLocation;
    private boolean isUpdatingFavoritesDropdown = false;

    public MainFrame() {
        super("WeatherApp — Monitorizare & Prognoză Meteo");
        this.apiService = new WeatherApiService();
        this.favoritesStorage = new FavoritesStorage();

        initUI();
        loadInitialWeather();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 700);
        setMinimumSize(new Dimension(840, 600));
        setLocationRelativeTo(null); // Centreaza fereastra pe ecran

        JPanel rootPanel = new JPanel(new BorderLayout(15, 15));
        rootPanel.setBorder(new EmptyBorder(15, 20, 20, 20));
        rootPanel.setBackground(new Color(241, 245, 249));

        // 1. Bara Superioara: Cautare + Favorite
        rootPanel.add(createTopBar(), BorderLayout.NORTH);

        // 2. Zona Centrala: Vreme curenta + Prognoza
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        currentWeatherPanel = new CurrentWeatherPanel();
        forecastPanel = new ForecastPanel();

        contentPanel.add(currentWeatherPanel);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(forecastPanel);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);

        rootPanel.add(scrollPane, BorderLayout.CENTER);

        // 3. Bara Inferioara: Stare / Notificari
        statusLabel = new JLabel("Gata");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(100, 116, 139));
        rootPanel.add(statusLabel, BorderLayout.SOUTH);

        setContentPane(rootPanel);
    }

    private JPanel createTopBar() {
        JPanel bar = new JPanel(new BorderLayout(10, 0));
        bar.setOpaque(false);

        // Zona de cautare (stanga)
        JPanel searchBox = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        searchBox.setOpaque(false);

        searchField = new JTextField(18);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.putClientProperty("JTextField.placeholderText", "Introdu un oraș (ex: Brașov)...");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performSearch(searchField.getText());
                }
            }
        });

        searchButton = new JButton("Caută");
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        searchButton.addActionListener(e -> performSearch(searchField.getText()));

        searchBox.add(new JLabel("Oraș:"));
        searchBox.add(searchField);
        searchBox.add(searchButton);

        // Zona de favorite (dreapta)
        JPanel favBox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        favBox.setOpaque(false);

        favoritesComboBox = new JComboBox<>();
        favoritesComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        refreshFavoritesDropdown();
        favoritesComboBox.addActionListener(e -> {
            if (!isUpdatingFavoritesDropdown) {
                Location selected = (Location) favoritesComboBox.getSelectedItem();
                if (selected != null) {
                    loadWeatherForLocation(selected);
                }
            }
        });

        favoriteToggleButton = new JButton("☆ Salvează");
        favoriteToggleButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        favoriteToggleButton.addActionListener(e -> toggleFavorite());

        favBox.add(new JLabel("Favorite:"));
        favBox.add(favoritesComboBox);
        favBox.add(favoriteToggleButton);

        bar.add(searchBox, BorderLayout.WEST);
        bar.add(favBox, BorderLayout.EAST);

        return bar;
    }

    private void performSearch(String query) {
        if (query == null || query.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Te rugăm să introduci un nume de oraș.", "Atenție", JOptionPane.WARNING_MESSAGE);
            return;
        }

        setLoading(true, "Căutare oraș: " + query + "...");

        apiService.searchLocationsAsync(query)
                .thenAccept(locations -> SwingUtilities.invokeLater(() -> {
                    if (locations.isEmpty()) {
                        setLoading(false, "Orașul nu a fost găsit.");
                        JOptionPane.showMessageDialog(this, 
                                "Nu a fost găsit niciun oraș cu denumirea: \"" + query + "\".", 
                                "Niciun rezultat", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        Location first = locations.get(0);
                        loadWeatherForLocation(first);
                    }
                }))
                .exceptionally(ex -> {
                    SwingUtilities.invokeLater(() -> {
                        setLoading(false, "Eroare la căutare.");
                        JOptionPane.showMessageDialog(this, 
                                "Eroare de conexiune la căutarea orașului: " + ex.getMessage(), 
                                "Eroare Rețea", JOptionPane.ERROR_MESSAGE);
                    });
                    return null;
                });
    }

    private void loadWeatherForLocation(Location location) {
        if (location == null) return;
        this.currentLocation = location;
        updateFavoriteButtonState();

        setLoading(true, "Preluare date meteo pentru " + location.getName() + "...");

        apiService.fetchWeatherAsync(location)
                .thenAccept(report -> SwingUtilities.invokeLater(() -> {
                    currentWeatherPanel.updateData(report);
                    forecastPanel.updateForecast(report.getDailyForecasts());
                    setLoading(false, "Date actualizate cu succes.");
                }))
                .exceptionally(ex -> {
                    SwingUtilities.invokeLater(() -> {
                        setLoading(false, "Eroare la preluarea vremii.");
                        JOptionPane.showMessageDialog(this, 
                                "Nu s-au putut prelua datele meteo: " + ex.getMessage(), 
                                "Eroare", JOptionPane.ERROR_MESSAGE);
                    });
                    return null;
                });
    }

    private void toggleFavorite() {
        if (currentLocation == null) return;

        if (favoritesStorage.isFavorite(currentLocation)) {
            favoritesStorage.removeFavorite(currentLocation);
        } else {
            favoritesStorage.addFavorite(currentLocation);
        }

        refreshFavoritesDropdown();
        updateFavoriteButtonState();
    }

    private void updateFavoriteButtonState() {
        if (currentLocation == null) {
            favoriteToggleButton.setEnabled(false);
            return;
        }
        favoriteToggleButton.setEnabled(true);
        if (favoritesStorage.isFavorite(currentLocation)) {
            favoriteToggleButton.setText("★ În favorite");
            favoriteToggleButton.setForeground(new Color(217, 119, 6)); // Amber / Gold
        } else {
            favoriteToggleButton.setText("☆ Salvează");
            favoriteToggleButton.setForeground(Color.BLACK);
        }
    }

    private void refreshFavoritesDropdown() {
        isUpdatingFavoritesDropdown = true;
        favoritesComboBox.removeAllItems();
        List<Location> favs = favoritesStorage.getFavorites();
        for (Location loc : favs) {
            favoritesComboBox.addItem(loc);
        }
        if (currentLocation != null && favoritesStorage.isFavorite(currentLocation)) {
            favoritesComboBox.setSelectedItem(currentLocation);
        }
        isUpdatingFavoritesDropdown = false;
    }

    private void loadInitialWeather() {
        List<Location> favs = favoritesStorage.getFavorites();
        if (!favs.isEmpty()) {
            loadWeatherForLocation(favs.get(0));
        } else {
            loadWeatherForLocation(new Location("București", "România", "București", 44.4323, 26.1063));
        }
    }

    private void setLoading(boolean loading, String message) {
        statusLabel.setText(message);
        searchButton.setEnabled(!loading);
        searchField.setEnabled(!loading);
    }
}
