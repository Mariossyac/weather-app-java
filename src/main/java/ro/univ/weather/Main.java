package ro.univ.weather;

import com.formdev.flatlaf.FlatLightLaf;
import ro.univ.weather.ui.MainFrame;

import javax.swing.*;

/**
 * Punctul principal de intrare in aplicatia WeatherApp.
 * Initializeaza tema moderna FlatLaf si lanseaza fereastra pe firul de executie grafic (EDT).
 */
public class Main {

    public static void main(String[] args) {
        // 1. Initializare tema FlatLaf (cu fallback pe System Look and Feel)
        try {
            FlatLightLaf.setup();
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                System.err.println("Nu s-a putut initializa tema grafica: " + ex.getMessage());
            }
        }

        // 2. Lansare interfata grafica pe Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

