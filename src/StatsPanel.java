import javax.swing.*;
import java.util.List;

//Displays basic statistics about avg, max, min gdp
public class StatsPanel extends JPanel implements Observer {
    private JLabel statsLabel;

    //constructor to include data in app
    public StatsPanel(List<GDPData> dataList) {
        statsLabel = new JLabel();
        update(dataList);
        add(statsLabel);
    }

    //Streams - basic part for let GUI working - updates the statistics on-time
    @Override
    public void update(List<GDPData> dataList) {
        double sum = dataList.stream().mapToDouble(GDPData::getGdp).sum();
        double max = dataList.stream().mapToDouble(GDPData::getGdp).max().orElse(0);
        double min = dataList.stream().mapToDouble(GDPData::getGdp).min().orElse(0);
        double avg = sum / dataList.size();

        statsLabel.setText(String.format("Average GDP: %.2f | Max GDP: %.2f | Min GDP: %.2f", avg, max, min));
    }
}
