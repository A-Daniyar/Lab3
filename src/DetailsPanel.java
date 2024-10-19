import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
    private JLabel countryLabel;
    private JLabel yearLabel;
    private JLabel gdpLabel;

    public DetailsPanel() {
        setLayout(new GridLayout(3,1));
        countryLabel = new JLabel();
        yearLabel = new JLabel();
        gdpLabel = new JLabel();

        add(countryLabel);
        add(yearLabel);
        add(gdpLabel);
    }

    public void updateDetails(GDPData data){
        countryLabel.setText("Country: " + data.getCountry());
        yearLabel.setText("Year: " + data.getYear());
        gdpLabel.setText("GDP: " + data.getGdp());
        revalidate();
        repaint(); // useful? or not?
    }
}
