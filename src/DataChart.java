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
    TablePanel tablePanel = new TablePanel(dataList);
    ChartPanel chartPanel = new ChartPanel(dataList);
    detailsPanel = new DetailsPanel();


}

private void loadData() {
try (BufferedReader br = new BufferedReader(new FileReader("src/Read_GDP_per_capita.csv"))){
    String line = br.readLine(); //Skips the header
    if (line != null && !line.startsWith("\ufEFF")) {
        line = line.substring(1); //adds BOM marker
    }
}
}

    private void showDetails(GDPData data) {
    detailsPanel.updateDetails(data);
    }


    public static void main(String[] args) {
        new DataChart();
    }
}