import CSV.CSV;
import CSV.Row;

import KMeans.KMeans;
import KMeans.KMeansEstimateType;

import Utilities.GetAbsolutePath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KMeansTest {
    private static CSV csvReader;
    private static KMeans kMeans;

    private final String irisWithOutLabels = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/irisWithOutLabel.csv");
    private final String emptyFile = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/EmptyFile.csv");
    private final String numbers = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/numbers.csv");

    @BeforeEach
    void setUp() {
        csvReader = new CSV();
        kMeans = new KMeans(4, 10, 9999999, KMeansEstimateType.knnType);
    }

    @Test
    @DisplayName("test train null table")
    void testTrainNullTable(){
        assertThrows(IllegalArgumentException.class, () -> kMeans.train(null));
    }

    @Test
    @DisplayName("test train empty table")
    void testTrainEmptyTable(){
        assertThrows(IllegalArgumentException.class, () -> kMeans.train(csvReader.readTable(emptyFile)));
    }

    @Test
    @DisplayName("test train different number of cluster")
    void testTrainDifferentNumberOfCluster(){
        assertThrows(UnsupportedOperationException.class, () -> kMeans.train(csvReader.readTable(numbers)));
    }

    @Test
    @DisplayName("test estimate null sample")
    void testEstimateNullSample(){
        kMeans.train(csvReader.readTable(irisWithOutLabels));
        assertThrows(IllegalArgumentException.class, () -> kMeans.estimate(null));
    }

    @Test
    @DisplayName("test estimate null sample")
    void testEstimateNullDataSample(){
        kMeans.train(csvReader.readTable(irisWithOutLabels));
        assertThrows(IllegalArgumentException.class, () -> kMeans.estimate(new Row(null)));
    }

    @Test
    @DisplayName("test estimate null sample")
    void testEstimateEmptyDataSample(){
        kMeans.train(csvReader.readTable(irisWithOutLabels));
        assertThrows(IllegalArgumentException.class, () -> kMeans.estimate(new Row(new ArrayList<>())));
    }

    @Test
    @DisplayName("test estimate null sample")
    void testEstimateDifferentSize(){
        kMeans.train(csvReader.readTable(irisWithOutLabels));

        List<Double> attributes = new ArrayList<>();
        attributes.add(7.0);
        attributes.add(3.2);

        assertThrows(UnsupportedOperationException.class, () -> kMeans.estimate(new Row(attributes)));
    }

    @Test
    @DisplayName("test estimate with knn cluster 2")
    void testEstimateKnnCluster2() {
        kMeans.train(csvReader.readTable(irisWithOutLabels));

        List<Double> attributes = new ArrayList<>();
        attributes.add(7.0);
        attributes.add(3.2);
        attributes.add(4.7);
        attributes.add(1.4);

        assertEquals("Cluster-2", kMeans.estimate(new Row(attributes)));
    }

    @Test
    @DisplayName("test estimate with knn cluster 4")
    void testEstimateKnnCluster4() {
        kMeans.train(csvReader.readTable(irisWithOutLabels));

        List<Double> attributes = new ArrayList<>();
        attributes.add(0.);
        attributes.add(0.);
        attributes.add(0.);
        attributes.add(0.);

        assertEquals("Cluster-4", kMeans.estimate(new Row(attributes)));
    }

    @Test
    @DisplayName("test estimate with mean cluster 1")
    void testEstimateMeanCluster1() {
        kMeans.train(csvReader.readTable(irisWithOutLabels));

        List<Double> attributes = new ArrayList<>();
        attributes.add(5.8);
        attributes.add(4.0);
        attributes.add(1.2);
        attributes.add(0.2);

        kMeans.setEstimateType(KMeansEstimateType.meanType);
        assertEquals("Cluster-1", kMeans.estimate(new Row(attributes)));
    }

    @Test
    @DisplayName("test estimate with mean cluster 4")
    void testEstimateMeanCluster4() {
        kMeans.train(csvReader.readTable(irisWithOutLabels));

        List<Double> attributes = new ArrayList<>();
        attributes.add(9.);
        attributes.add(9.);
        attributes.add(9.);
        attributes.add(9.);

        kMeans.setEstimateType(KMeansEstimateType.meanType);
        assertEquals("Cluster-4", kMeans.estimate(new Row(attributes)));
    }

}