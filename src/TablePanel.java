import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public TablePanel(List<GDPData> dataList, Consumer<GDPData> onRowSelected) {
        setLayout(new BorderLayout());

        String[] columns = {"Country", "Year", "GDP"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        populateTable(dataList);

        table.getSelectionModel().addListSelectionListener(e ->  {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >=0) {
                String country = (String) table.getValueAt(selectedRow, 0);
                int year = (Integer) table.getValueAt(selectedRow, 1);
                double gdp = (Double) table.getValueAt(selectedRow, 2);
                onRowSelected.accept(new GDPData(country,year,gdp));
            }
        });
    }

    private void populateTable(List<GDPData> dataList) {
        tableModel.setRowCount(0);
        dataList.forEach(data -> tableModel.addRow(new Object[]{
                data.getCountry(), data.getYear(), data.getGdp()
        }));
    }
}
