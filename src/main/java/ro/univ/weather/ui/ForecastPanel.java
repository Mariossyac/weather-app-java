package ro.univ.weather.ui;

import ro.univ.weather.model.DailyForecast;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * Panou orizontal care contine cardurile de prognoza pentru urmatoarele 7 zile.
 */
public class ForecastPanel extends JPanel {

    private final JPanel cardsContainer;

    public ForecastPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel sectionTitle = new JLabel("Prognoză pe 7 zile");
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sectionTitle.setForeground(new Color(30, 41, 59));
        sectionTitle.setBorder(new EmptyBorder(15, 5, 10, 5));
        add(sectionTitle, BorderLayout.NORTH);

        cardsContainer = new JPanel(new GridLayout(1, 7, 10, 0));
        cardsContainer.setOpaque(false);
        add(cardsContainer, BorderLayout.CENTER);
    }

    /**
     * Reconstruieste cardurile zilnice pe baza listei de prognoze.
     */
    public void updateForecast(List<DailyForecast> forecasts) {
        cardsContainer.removeAll();

        if (forecasts == null || forecasts.isEmpty()) {
            cardsContainer.revalidate();
            cardsContainer.repaint();
            return;
        }

        for (DailyForecast df : forecasts) {
            cardsContainer.add(createDailyCard(df));
        }

        cardsContainer.revalidate();
        cardsContainer.repaint();
    }

    private JPanel createDailyCard(DailyForecast df) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                new EmptyBorder(12, 8, 12, 8)
        ));

        // 1. Ziua saptamanii
        JLabel dayLabel = new JLabel(df.getDayOfWeekFormatted());
        dayLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        dayLabel.setForeground(new Color(30, 41, 59));
        dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 2. Data scurta
        JLabel dateLabel = new JLabel(df.getShortDateFormatted());
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dateLabel.setForeground(new Color(100, 116, 139));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 3. Iconita meteo
        JLabel iconLabel = new JLabel(df.getIconEmoji());
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 4. Temperaturi max / min
        JLabel tempLabel = new JLabel(String.format(java.util.Locale.US, "%.0f° / %.0f°", df.getMaxTemperature(), df.getMinTemperature()));
        tempLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tempLabel.setForeground(new Color(15, 23, 42));
        tempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 5. Descriere scurta
        JLabel descLabel = new JLabel(df.getDescription());
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        descLabel.setForeground(new Color(71, 85, 105));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(dayLabel);
        card.add(dateLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(iconLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(tempLabel);
        card.add(Box.createVerticalStrut(4));
        card.add(descLabel);

        return card;
    }
}

