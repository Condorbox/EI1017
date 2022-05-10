import CSV.AI.KNN.KNN;

import Patterns.FactoryPattern.DistanceType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FactoryPatternTest {

    private static KNN knn;

    List<Double> data = List.of(3., 4., 5., 6.);
    List<Double> sample = List.of(1., 2., 3., 4.);

    List<Double> data2 = List.of(1.36, 3.17, 0.51, 5.55);
    List<Double> sample2 = List.of(3.83, 1.24, 9.97, 6.77);

    @BeforeAll
    static void initAll(){
        knn = new KNN();
    }

    @Test
    @DisplayName("Test Euclidean distance")
    void calculateDistanceEuclidean() {
        knn.setDistance(DistanceType.EUCLIDEAN);
        assertEquals(4.0, knn.calculateDistance(data, sample));
    }
    @Test
    @DisplayName("Test Euclidean distance Again")
    void calculateDistanceEuclideanAgain() {
        knn.setDistance(DistanceType.EUCLIDEAN);
        assertEquals(10.04020916116791, knn.calculateDistance(data2, sample2));
    }

    @Test
    @DisplayName("Test Manhattan distance")
    void calculateDistanceManhattan() {
        knn.setDistance(DistanceType.MANHATTAN);
        assertEquals(8, knn.calculateDistance(data, sample));
    }

    @Test
    @DisplayName("Test Manhattan distance Again")
    void calculateDistanceManhattanAgain() {
        knn.setDistance(DistanceType.MANHATTAN);
        assertEquals(15.079999999999998, knn.calculateDistance(data2, sample2));
    }
}