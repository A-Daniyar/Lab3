import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataChart extends JFrame {
    private List<GDPData> dataList = new ArrayList<>();
    private DetailsPanel detailsPanel;

    public DataChart() {
    setTitle("Real GDP per Capita Chart");
    setLayout(new BorderLayout());

    loadData();

    //Panels initialization
    StatsPanel statsPanel = new StatsPanel(dataList);
    TablePanel tablePanel = new TablePanel(dataList, this::showDetails);
    ChartPanel chartPanel = new ChartPanel(dataList);
    detailsPanel = new DetailsPanel();

    add(statsPanel, BorderLayout.NORTH);
    add(tablePanel, BorderLayout.CENTER);
    add(chartPanel, BorderLayout.EAST);
    add(detailsPanel, BorderLayout.SOUTH);

    setSize(1000, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    }

    //Loading data from excel file
    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Real_GDP_per_capita.csv"))) {
            String line = br.readLine(); //Skips the header
            if (line != null && line.startsWith("\ufEFF")) {
                line = line.substring(1); //adds BOM marker
            }
            while ((line = br.readLine()) != null) { // list of ignored symbols
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (values.length < 4) continue;

                String country = values[0].trim();
                String gdpString = values[2].replaceAll("[^0-9.]", "").trim();
                if (gdpString.isEmpty()) continue;

                double gdp = Double.parseDouble(gdpString);
                int year = Integer.parseInt(values[3].trim());

                dataList.add(new GDPData(country, year, gdp));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Shows details of selected row
    private void showDetails(GDPData data) {
    detailsPanel.updateDetails(data);
    }


    public static void main(String[] args) {
        new DataChart();
    }
}