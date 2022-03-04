package KNN;

import CSV.TableWithLabel;
import java.util.List;

public interface KNNInterface {
    public void train(TableWithLabel data);
    String estimate(List<Double> sample);
}
