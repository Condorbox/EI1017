package CSV;

import java.util.List;

public interface TableInterface<T> {
    T getRowAt(int index);
    List<Double> getColumAt(int index);
    void addHeader(String header);
    List<String> getHeaders();
    List<T> getDataTable();
}
