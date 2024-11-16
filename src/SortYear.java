import java.util.Comparator;
import java.util.List;

public class SortYear implements SortByStrategy{
    @Override
    public void sort(List<GDPData> dataList, boolean ascending) {
        Comparator<GDPData> comparator = Comparator.comparing(GDPData::getYear);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        dataList.sort(comparator);
    }
}