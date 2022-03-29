package AI.LinearRegresion;

import CSV.Row;
import CSV.Table;
import Utilities.AlgorithmInterface;

public interface LinearRegressionInterface extends AlgorithmInterface<Table<Row>, Double, Double> {
    Double[] estimateWithError(Double sample);
    Double getSlope();
    Double getOrigin();
    Double getError();
}
