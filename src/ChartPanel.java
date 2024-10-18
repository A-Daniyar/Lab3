import javax.swing.*;
import java.util.List;
import java.awt.*;

public class ChartPanel extends JPanel {
    public ChartPanel(List<GDPData> dataList) {
        this.dataList = dataList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
if (dataList == null || dataList.isEmpty()) return;

    }
}
