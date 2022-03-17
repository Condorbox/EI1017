package KMeans;

import CSV.CSV;
import CSV.Row;

import Utilities.GetAbsolutePath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KMeansTest {
    private static CSV csvReader;
    private static KMeans kMeans;

    private final String irisWithOutLabels = GetAbsolutePath.getAbsolutePathFromResource("/CSVFiles/irisWithOutLabel.csv");

    @BeforeEach
    void setUp() {
        csvReader = new CSV();
        kMeans = new KMeans(4, 4, 45682);
    }

    //TODO finish test I think it's wrong
    @Test
    void train() {
        kMeans.train(csvReader.readTable(irisWithOutLabels));
    }

    @Test
    void estimate() {
        kMeans.train(csvReader.readTable(irisWithOutLabels));

        List<Double> attributes = new ArrayList<>();
        attributes.add(5.1);
        attributes.add(3.5);
        attributes.add(1.4);
        attributes.add(0.2);

        assertEquals("Cluster-2", kMeans.estimate(new Row(attributes)));
    }
}