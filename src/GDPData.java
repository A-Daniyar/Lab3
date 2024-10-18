public class GDPData {
    private String country;
    private int year;
    private double gdp;

    public GDPData(String country, int year, double gdp) {
    this.country = country;
    this.year = year;
    this.gdp = gdp;
    }

    public String getCountry() { //getter for Country
    return country;
    }

    public int getYear() { // getter for Year
    return year;
    }

    public double getGdp() { // getter for GDP
    return gdp;
    }
}
//Should be ready