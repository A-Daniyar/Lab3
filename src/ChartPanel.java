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
        int margin = 50;
        int width = getWidth() - margin * 2;
        int height = getHeight() - margin * 2;
        double maxGdp = dataList.stream().mapToDouble(GDPData::getGdp).max().orElse(1);

        g2.drawLine(margin, margin, margin, margin+height); //Y - axis
        //complete, check the video from youtube later
    }
}
