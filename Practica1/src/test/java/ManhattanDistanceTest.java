import Patterns.StrategyPattern.ManhattanDistance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManhattanDistanceTest {
    private static ManhattanDistance distance;

    List<Double> dataNull = null;
    List<Double> dataEmpty = List.of();
    List<Double> dataOneElement = List.of(5.);
    List<Double> dataSameElements =List.of(4.,4.,4.,4.);
    List<Double> data = List.of(1.,2.,3.,4.,5.);
    List<Double> data2 = List.of(3.,5.,-1.,2.,7.);

    @BeforeAll
    static void initAll() {
        distance = new ManhattanDistance();
    }

    @Test
    void calculateDistance() {
    }

    @Test
    @DisplayName("Test Distance null")
    void testDistanceNull(){
        assertThrows(NullPointerException.class, () -> distance.calculateDistance(dataNull, dataSameElements));
    }

    @Test
    @DisplayName("Test Distance different data size")
    void testDistanceDifferentSize(){
        assertThrows(IllegalArgumentException.class, () -> distance.calculateDistance(dataOneElement, dataSameElements));
    }

    @Test
    @DisplayName("Test Distance empty")
    void testDistanceEmpty(){
        assertEquals(0, distance.calculateDistance(dataEmpty, dataEmpty));
    }

    @Test
    @DisplayName("Test Distance one element")
    void testDistanceOneElement(){
        assertEquals(0, distance.calculateDistance(dataOneElement, dataOneElement));
    }

    @Test
    @DisplayName("Test Distance same elements")
    void testDistanceSameElements(){
        assertEquals(0, distance.calculateDistance(dataSameElements, dataSameElements));
    }

    @Test
    @DisplayName("Test Distance")
    void testDistance(){
        assertEquals(13.0, distance.calculateDistance(data2, data));
    }

}