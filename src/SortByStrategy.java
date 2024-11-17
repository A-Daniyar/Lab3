import java.util.List;

//Defines a contract for sorting strategies
public interface SortByStrategy {
    void sort(List<GDPData> dataList, boolean ascending);
}
