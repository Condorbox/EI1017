package AI.KNN;

import CSV.TableWithLabel;
import Utilities.AlgorithmInterface;

import java.util.List;
import java.util.Map;

public interface KNNInterface extends AlgorithmInterface<TableWithLabel,List<Double>, String> {
    Map<List<Double>,String> getDataTable();
    List<String> getHeader();
}
