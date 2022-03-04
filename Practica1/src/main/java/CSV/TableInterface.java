package CSV;

import java.util.List;

public interface TableInterface<T> {
    T getRowAt(int index);
    List<Double> getColumnAt(int index);
    void addHeader(String header);
    void addRow(T row);
    List<String> getHeaders();
    List<T> getDataTable();
}
