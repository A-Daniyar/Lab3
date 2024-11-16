import java.util.List;

public interface SortByStrategy {
    void sort(List<GDPData> dataList, boolean ascending);
}
