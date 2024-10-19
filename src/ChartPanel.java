import javax.swing.*;
import java.util.List;
import java.awt.*;

public class ChartPanel extends JPanel {
    private List<GDPData> dataList;

    public ChartPanel(List<GDPData> dataList) {
        this.dataList = dataList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dataList == null || dataList.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        int margin = 40;
        int width = getWidth() - margin * 2;
        int height = getHeight() - margin * 2;
        double maxGdp = dataList.stream().mapToDouble(GDPData::getGdp).max().orElse(1);

        g2.drawLine(margin, margin, margin, margin+height); //Y-axis
        g2.drawLine(margin, margin+height, margin+width, margin+height); //X-axis

        int xInterval = width / (dataList.size() - 1); //Distance between X-axis
        int prevX = margin;
        int prevY = height + margin - (int) ((dataList.get(0).getGdp() / maxGdp) * height);

        for (int i = 1; i < dataList.size(); i++) {
            int x = margin + i * xInterval;
            int y = height + margin - (int) ((dataList.get(i).getGdp() / maxGdp) * height);
            g2.drawLine(prevX, prevY, x, y);
            prevX = x;
            prevY = y;
        }
    }
}
