import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TablePanel extends JPanel implements Subject{
    private JTable table;
    private DefaultTableModel tableModel;
    private List<GDPData> dataList;
    private List<Observer> observers = new ArrayList<>();
    private SortByStrategy sortByStrategy;

    private boolean isAscending = true;

    public TablePanel(List<GDPData> dataList) {
        this.dataList = dataList;

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
            sortByStrategy = new SortCountry();
            sortData();
        });
        sortYear.addActionListener(e -> {
            sortByStrategy = new SortYear();
            sortData();
        });
        sortGDP.addActionListener(e -> {
            sortByStrategy = new SortYear();
            sortData();
        });
    }

    private void populateTable(List<GDPData> dataList) {
        tableModel.setRowCount(0);
        dataList.forEach(data -> tableModel.addRow(new Object[]{
                data.getCountry(), data.getYear(), data.getGdp()
        }));
    }

    private void sortData() {
        if (sortByStrategy != null) {
            sortByStrategy.sort(dataList, isAscending);
            isAscending = !isAscending;
            populateTable(dataList);
            notifyObservers();
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(dataList);
        }
    }
}
