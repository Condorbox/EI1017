import java.util.List;

public interface TableInterface {
    Row getRowAt(int index);
    List<Double> getColumAt(int index);
    void addHeader(String header);
    void addRow(Row row);
    List<String> getHeaders();
    List<Row> getDataTable();
}
