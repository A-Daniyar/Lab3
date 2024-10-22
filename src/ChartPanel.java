import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPanel extends JPanel {
    private List<GDPData> dataList;  // Holds the data for the chart

    public ChartPanel(List<GDPData> dataList) {
        this.dataList = dataList;
        setPreferredSize(new Dimension(400, 600)); // Adjust chart size
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (dataList == null || dataList.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        int barHeight = 15; // Height of each bar
        int spacing = 5;   // Space between bars
        int margin = 100;    // Left margin

        double maxGdp = dataList.stream().mapToDouble(GDPData::getGdp).max().orElse(1); // Max GDP value

        int y = spacing;  // Initial Y position

        for (GDPData data : dataList) {
            // Scale the bar width based on the GDP value
            int barWidth = (int) ((data.getGdp() / maxGdp) * (getWidth() - margin * 2));

            // Draw the bar
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(margin, y, barWidth, barHeight);

            // Draw a border around the bar
            g2.setColor(Color.BLACK);
            g2.drawRect(margin, y, barWidth, barHeight);

            // Draw the label (Country and GDP value) next to the bar
            g2.drawString(data.getCountry() + " (" + data.getGdp() + ")", margin - 100, y + barHeight / 2 + 5);

            y += barHeight + spacing;  // Move to the next bar's position
        }
    }

    // Method to update the chart with new data
    public void updateChart(List<GDPData> newDataList) {
        this.dataList = newDataList;
        revalidate();
        repaint();
    }
}
