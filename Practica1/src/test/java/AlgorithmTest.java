import CSV.CSV;
import KNN.KNN;
import LinearRegresion.LinearRegression;
import Utilities.AlgorithmInterface;
import Utilities.GetAbsolutePath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmTest {
    private static CSV CSVReader;
    AlgorithmInterface algorithm;

    private final String milesDollars = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/miles_dollars.csv");
    private final String iris = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/iris.csv");

    @BeforeAll
    static void initAll(){
        CSVReader = new CSV();
    }

    @Test
    @DisplayName("Test Algorithm Interface Linear Regression")
    void TestLinearRegression(){
        algorithm = new LinearRegression();
        algorithm.train(CSVReader.readTable(milesDollars));
        assertEquals(286.502613676227, algorithm.estimate(10.));
    }

    @Test
    @DisplayName("Test Algorithm Interface KNN")
    void TestKNN(){
        algorithm = new KNN();
        algorithm.train(CSVReader.readTableWithLabels(iris));
        List<Double> sample = new ArrayList<>();

        sample.add(6.1);
        sample.add(3.0);
        sample.add(4.6);
        sample.add(1.4);

        assertEquals("Iris-versicolor", algorithm.estimate(sample));
    }
}
