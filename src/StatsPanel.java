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
    }
}
