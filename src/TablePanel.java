import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<GDPData> dataList;
    private ChartPanel chartPanel;  // Reference to ChartPanel

    private boolean isCountryAscending = true;
    private boolean isYearAscending = true;
    private boolean isGDPAscending = true;

    public TablePanel(List<GDPData> dataList, Consumer<GDPData> onRowSelected, ChartPanel chartPanel) {
        this.dataList = dataList;
        this.chartPanel = chartPanel;  // Store the ChartPanel reference

        setLayout(new BorderLayout());

        String[] columns = {"Country", "Year", "GDP"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel sortingPanel = new JPanel(new FlowLayout());
        JButton sortCountry = new JButton("Sort by Country");
        JButton sortYear = new JButton("Sort by Year");
        JButton sortGDP = new JButton("Sort by GDP");

        sortingPanel.add(sortCountry);
        sortingPanel.add(sortYear);
        sortingPanel.add(sortGDP);
        add(sortingPanel, BorderLayout.NORTH);

        populateTable(dataList);

        sortCountry.addActionListener(e -> {
            sortData(Comparator.comparing(GDPData::getCountry), isCountryAscending);
            isCountryAscending = !isCountryAscending;
        });
        sortYear.addActionListener(e -> {
            sortData(Comparator.comparing(GDPData::getYear), isYearAscending);
            isYearAscending = !isYearAscending;
        });
        sortGDP.addActionListener(e -> {
            sortData(Comparator.comparing(GDPData::getGdp), isGDPAscending);
            isGDPAscending = !isGDPAscending;
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String country = (String) table.getValueAt(selectedRow, 0);
                int year = (Integer) table.getValueAt(selectedRow, 1);
                double gdp = (Double) table.getValueAt(selectedRow, 2);
                onRowSelected.accept(new GDPData(country, year, gdp));
            }
        });
    }

    private void populateTable(List<GDPData> dataList) {
        tableModel.setRowCount(0);
        dataList.forEach(data -> tableModel.addRow(new Object[]{
                data.getCountry(), data.getYear(), data.getGdp()
        }));
    }

    private void sortData(Comparator<GDPData> comparator, boolean ascending) {
        if (!ascending) {
            comparator = comparator.reversed();
        }
        dataList.sort(comparator);
        populateTable(dataList);
        chartPanel.updateChart(dataList);  // Update the chart with new sorted data
    }
}
