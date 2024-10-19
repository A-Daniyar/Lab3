import javax.swing.*;
import java.util.List;

public class StatsPanel extends JPanel {
private JLabel statsLabel;

    public StatsPanel(List<GDPData> dataList) {
        statsLabel = new JLabel();
        updateStats(dataList);
        add(statsLabel);
    }

    public void updateStats(List<GDPData> dataList) {
        double sum = dataList.stream().mapToDouble(GDPData::getGdp).sum();
        double max = dataList.stream().mapToDouble(GDPData::getGdp).max().orElse(0);
        double min = dataList.stream().mapToDouble(GDPData::getGdp).min().orElse(0);
        double avg =sum / dataList.size();

        statsLabel.setText(String.format("Average GDP: %.2f | Max GDP: %.2f | Min GDP: %.2f", avg, max, min));
    }
}
