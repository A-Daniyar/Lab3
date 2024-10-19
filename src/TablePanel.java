import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<GDPData> dataList; //data for sorting

    //Flags for asdend and descend the data
    private boolean isCountryAscending = true;
    private boolean isYearAscending = true;
    private boolean isGDPAscending = true;

    public TablePanel(List<GDPData> dataList, Consumer<GDPData> onRowSelected) {
        this.dataList = dataList;

        setLayout(new BorderLayout());

        String[] columns = {"Country", "Year", "GDP"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        //Sorting buttons
        JPanel sortingPanel = new JPanel(new FlowLayout());
        JButton sortCountry = new JButton("Sort by Country");
        JButton sortYear = new JButton("Sort by Year");
        JButton sortGDP = new JButton("Sort by GDP");

        sortingPanel.add(sortCountry);
        sortingPanel.add(sortYear);
        sortingPanel.add(sortGDP);
        add(sortingPanel, BorderLayout.NORTH);

        //initial populateTable
        populateTable(dataList);

        //Listeners of sorting buttons
        sortCountry.addActionListener(e -> {
            sortData(Comparator.comparing(GDPData::getCountry), isCountryAscending);
            isCountryAscending = !isCountryAscending; //Toggles the flag
        });
        sortYear.addActionListener(e ->
        {sortData(Comparator.comparing(GDPData::getYear), isYearAscending);
        isYearAscending = !isYearAscending;
        });
        sortGDP.addActionListener(e -> {
                sortData(Comparator.comparing(GDPData::getGdp), isGDPAscending);
                isGDPAscending = !isGDPAscending;
        });


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
        tableModel.setRowCount(0); //clears the table
        dataList.forEach(data -> tableModel.addRow(new Object[]{
                data.getCountry(), data.getYear(), data.getGdp()
        }));
    }

    private void sortData(Comparator<GDPData> comparator, boolean ascending) {
        if(!ascending) {
            comparator = comparator.reversed(); //reverses the comparator if descending
        }
        dataList.sort(comparator); //Sorts the data
        populateTable(dataList); //Refreshes the data
    }
}
