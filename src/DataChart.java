import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataChart extends JFrame {     //Main class
    private List<GDPData> dataList;
    private DetailsPanel detailsPanel;

    public DataChart() {    //Constructor
        setTitle("Real GDP per Capita Chart");
        setLayout(new BorderLayout()); //arranges other components

        dataList = new ArrayList<>();
        loadData(); //initial loadData from class below

        //Panels initialization
        StatsPanel statsPanel = new StatsPanel(dataList);
        ChartPanel chartPanel = new ChartPanel(dataList);
        TablePanel tablePanel = new TablePanel(dataList);
        detailsPanel = new DetailsPanel();

        //Linked observers
        tablePanel.addObserver(statsPanel);
        tablePanel.addObserver(chartPanel);

        //Adds panels to the frame, adjusts them
        add(statsPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(chartPanel, BorderLayout.EAST);
        add(detailsPanel, BorderLayout.SOUTH);

    setSize(1000, 600); //window size adjuster
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes on exit
    setVisible(true); //Makes the GUI visible

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
                if (values.length < 4) continue; //ensures it to all columns

                String country = values[0].trim();
                String gdpString = values[2].replaceAll("[^0-9.]", "").trim(); //leaves only GDP value
                if (gdpString.isEmpty()) continue; //Skips empty gdps

                double gdp = Double.parseDouble(gdpString);
                int year = Integer.parseInt(values[3].trim());

                dataList.add(new GDPData(country, year, gdp));
            }
        }
        catch (Exception e) {
            e.printStackTrace(); //Prints errors
        }
    }

    //Shows details of selected row
    private void showDetails(GDPData data) {
    detailsPanel.updateDetails(data);
    }

    //Launches this app
    public static void main(String[] args) {
        new DataChart();
    }
}