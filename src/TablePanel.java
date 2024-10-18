import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public TablePanel(List<GDPData> dataList, Consumer<GDPData> onRowSelected) {
        setLayout(new BorderLayout());

        String[] columns = {"Country", "Year", "GDP"};

    }
}
