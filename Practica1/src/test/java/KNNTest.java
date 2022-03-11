import CSV.CSV;
import KNN.KNN;
import Utilities.GetAbsolutePath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {
    private static CSV CSVReader;
    private static KNN knn;

    private final String numbersWithLabels = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/numbersWithLabels.csv");
    private final String iris = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/iris.csv");

    @BeforeAll
    static void initAll(){
        CSVReader = new CSV();
    }

    @BeforeEach
    public void initEach(){
        knn = new KNN();
    }

    @Test
    @DisplayName("Test train number with labels")
    void testTrain() {
        knn.train(CSVReader.readTableWithLabels(numbersWithLabels));
        Map<List<Double>, String> expected = createExpectedMap();
        assertEquals(expected, knn.getDataTable());
    }

    private Map<List<Double>, String> createExpectedMap(){
        Map<List<Double>, String> expected = new HashMap<>();
        List<Double> key = new ArrayList<>();
        key.add(0.);
        key.add(0.);
        expected.put(key, "c0");

        key = new ArrayList<>();
        key.add(1.);
        key.add(2.);
        expected.put(key, "c1");

        key = new ArrayList<>();
        key.add(2.);
        key.add(4.);
        expected.put(key, "c2");

        key = new ArrayList<>();
        key.add(3.);
        key.add(6.);
        expected.put(key, "c3");

        return expected;
    }

    @Test
    @DisplayName("Test estimate sample size different than columns")
    void testEstimateSampleSizeDifferentThanColumns(){
        knn.train(CSVReader.readTableWithLabels(numbersWithLabels));
        assertThrows(IllegalArgumentException.class, () -> knn.estimate(new LinkedList<>()));
    }

    @Test
    @DisplayName("Test estimate K bigger than number of rows")
    void testEstimateKBiggerThanNumberOfRows(){
        knn.train(CSVReader.readTableWithLabels(numbersWithLabels));
        List<Double> sample = new ArrayList<>();

        sample.add(3.);
        sample.add(10.);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> knn.estimate(sample));
    }

    @Test
    @DisplayName("test estimate Iris-versicolor")
    void testEstimateIrisVersicolor(){
        knn.train(CSVReader.readTableWithLabels(iris));
        List<Double> sample = new ArrayList<>();

        sample.add(6.6);
        sample.add(3.0);
        sample.add(4.4);
        sample.add(1.4);

        assertEquals("Iris-versicolor", knn.estimate(sample));
    }

    @Test
    @DisplayName("test estimate Iris-versicolor again")
    void testEstimateIrisVersicolorAgain(){
        knn.train(CSVReader.readTableWithLabels(iris));
        List<Double> sample = new ArrayList<>();

        sample.add(6.1);
        sample.add(3.0);
        sample.add(4.6);
        sample.add(1.4);

        assertEquals("Iris-versicolor", knn.estimate(sample));
    }

    @Test
    @DisplayName("test estimate Iris-setosa")
    void testEstimateIrisSetosa(){
        knn.train(CSVReader.readTableWithLabels(iris));
        List<Double> sample = new ArrayList<>();

        sample.add(5.1);
        sample.add(3.8);
        sample.add(1.5);
        sample.add(0.3);

        assertEquals("Iris-setosa", knn.estimate(sample));
    }

    @Test
    @DisplayName("test estimate Iris-setosa again")
    void testEstimateIrisSetosaAgain(){
        knn.train(CSVReader.readTableWithLabels(iris));
        List<Double> sample = new ArrayList<>();

        sample.add(5.0);
        sample.add(3.3);
        sample.add(1.4);
        sample.add(0.2);

        assertEquals("Iris-setosa", knn.estimate(sample));
    }

    @Test
    @DisplayName("test estimate Iris-virginica")
    void testEstimateIrisVirginica() {
        knn.train(CSVReader.readTableWithLabels(iris));
        List<Double> sample = new ArrayList<>();

        sample.add(5.9);
        sample.add(3.0);
        sample.add(5.1);
        sample.add(1.8);

        assertEquals("Iris-virginica", knn.estimate(sample));
    }

    @Test
    @DisplayName("test estimate Iris-virginica again")
    void testEstimateIrisVirginicaAgain() {
        knn.train(CSVReader.readTableWithLabels(iris));
        List<Double> sample = new ArrayList<>();

        sample.add(6.3);
        sample.add(3.3);
        sample.add(6.0);
        sample.add(2.5);

        assertEquals("Iris-virginica", knn.estimate(sample));
    }
}