import CSV.CSV;
import CSV.Row;

import AI.KMeans.KMeans;
import AI.KMeans.KMeansEstimateType;

import AI.KNN.KNN;

import AI.LinearRegresion.LinearRegression;

import Patterns.StrategyPattern.EuclideanDistance;
import Utilities.AlgorithmInterface;
import Utilities.GetAbsolutePath;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmTest {
    private static CSV csvReader;
    AlgorithmInterface algorithm;

    private final String milesDollars = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/miles_dollars.csv");
    private final String iris = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/iris.csv");
    private final String irisWithOutLabels = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/irisWithOutLabel.csv");

    @BeforeAll
    static void initAll(){
        csvReader = new CSV();
    }

    @Test
    @DisplayName("Test Algorithm Interface Linear Regression")
    void testLinearRegression(){
        algorithm = new LinearRegression();
        algorithm.train(csvReader.readTable(milesDollars));
        assertEquals(286.502613676227, algorithm.estimate(10.));
    }

    @Test
    @DisplayName("Test Algorithm Interface KNN")
    void testKNN(){
        algorithm = new KNN();
        algorithm.train(csvReader.readTableWithLabels(iris));
        List<Double> sample = new ArrayList<>();

        sample.add(6.1);
        sample.add(3.0);
        sample.add(4.6);
        sample.add(1.4);

        assertEquals("Iris-versicolor", algorithm.estimate(sample));
    }

    @Test
    @DisplayName("Test Algorithm Interface KMeans")
    void testKMeans(){
        algorithm = new KMeans(4, 4, 9999999, KMeansEstimateType.knnType, new EuclideanDistance());
        algorithm.train(csvReader.readTable(irisWithOutLabels));

        List<Double> attributes = new ArrayList<>();
        attributes.add(7.0);
        attributes.add(3.2);
        attributes.add(4.7);
        attributes.add(1.4);

        assertEquals("Cluster-2", algorithm.estimate(new Row(attributes)));
    }
}
