import java.util.List;

//Defines a method for receiving an updates from subject
public interface Observer {
    void update(List<GDPData> dataList);
}
