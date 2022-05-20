import Utilities.Arithmetic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticTest {

    double[] dataNull = null;
    double[] dataEmpty = {};
    double[] dataOneElement = {5};
    double[] dataSameElements = {4,4,4,4};
    double[] data = {1,2,3,4,5};

    ///Mean
    @Test
    @DisplayName("Test mean null data")
    void testNullMean() {
        assertThrows(NullPointerException.class, () -> Arithmetic.mean(dataNull));
    }

    @Test
    @DisplayName("Test mean empty data")
    void testEmptyMean(){
        assertThrows(ArithmeticException.class, () -> Arithmetic.mean(dataEmpty));
    }

    @Test
    @DisplayName("Test mean one element")
    void testOneElementMean(){
        assertEquals(5, Arithmetic.mean(dataOneElement));
    }

    @Test
    @DisplayName("Test mean same elements")
    void testSameElementsMean(){
        assertEquals(4, Arithmetic.mean(dataSameElements));
    }

    @Test
    @DisplayName("Test mean")
    void testMean(){
        assertEquals(3, Arithmetic.mean(data));
    }

    //Variance
    @Test
    @DisplayName("Test Variance null data")
    void testNullVariance(){
        assertThrows(NullPointerException.class, () -> Arithmetic.variance(dataNull));
    }

    @Test
    @DisplayName("Test variance empty data")
    void testEmptyVariance(){
        assertThrows(NullPointerException.class, () -> Arithmetic.variance(dataNull));
    }

    @Test
    @DisplayName("Test variance one element")
    void testOneElementVariance(){
        assertEquals(0,Arithmetic.variance(dataOneElement));
    }

    @Test
    @DisplayName("Test variance same elements")
    void testSameElementsVariance(){
        assertEquals(0, Arithmetic.variance(dataSameElements));
    }

    @Test
    @DisplayName("Test Standard Deviation")
    void testVariance(){
        assertEquals(2, Arithmetic.variance(data));
    }

    //Standard Deviation
    @Test
    @DisplayName("Test Standard Deviation null data")
    void testNullStandardDeviation(){
        assertThrows(NullPointerException.class, () -> Arithmetic.standardDeviation(dataNull));
    }

    @Test
    @DisplayName("Test Standard Deviation empty data")
    void testEmptyStandardDeviation(){
        assertThrows(ArithmeticException.class, () -> Arithmetic.standardDeviation(dataEmpty));
    }

    @Test
    @DisplayName("Test Standard Deviation one element")
    void testOneElementStandardDeviation(){
        assertEquals(0,Arithmetic.standardDeviation(dataOneElement));
    }

    @Test
    @DisplayName("Test Standard Deviation same elements")
    void testSameElementsStandardDeviation(){
        assertEquals(0, Arithmetic.standardDeviation(dataSameElements));
    }

    @Test
    @DisplayName("Test Standard Deviation")
    void testStandardDeviation(){
        assertEquals(Math.sqrt(2), Arithmetic.standardDeviation(data));
    }

    ///Covariance
    @Test
    @DisplayName("Test Covariance null data")
    void testNullCovariance(){
        assertThrows(NullPointerException.class, () -> Arithmetic.Covariance(dataNull, dataNull));
    }

    @Test
    @DisplayName("Test Covariance empty data")
    void testEmptyCovariance(){
        assertThrows(ArithmeticException.class, () -> Arithmetic.Covariance(dataEmpty, dataEmpty));
    }

    @Test
    @DisplayName("Test Covariance different data size")
    void testDifferentSizeCovariance(){
        assertThrows(IllegalArgumentException.class, () -> Arithmetic.Covariance(dataSameElements, data));
    }

    @Test
    @DisplayName("Test Covariance one element")
    void testOneElementCovariance(){
        assertEquals(0, Arithmetic.Covariance(dataOneElement, dataOneElement));
    }

    @Test
    @DisplayName("Test Covariance same elements")
    void testSameElementsCovariance(){
        assertEquals(0, Arithmetic.Covariance(dataSameElements, dataSameElements));
    }

    @Test
    @DisplayName("Test Covariance")
    void testCovariance(){
        assertEquals(2, Arithmetic.Covariance(data, data));
    }
}