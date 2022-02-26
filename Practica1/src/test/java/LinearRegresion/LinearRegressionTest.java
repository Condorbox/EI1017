package LinearRegresion;

import CSV.CSV;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearRegressionTest {
    private static CSV CSVReader;
    private static LinearRegression linearRegression;

    @BeforeAll
    static void initAll(){
        linearRegression = new LinearRegression();
        CSVReader = new CSV();
    }

    ///Train
    @Test
    @DisplayName("Test empty file")
    void testEmptyFileTrain(){
        assertThrows(IllegalArgumentException.class, () -> linearRegression.train(CSVReader.readTable("CSVFiles/EmptyFile.csv")));
    }

    @Test
    @DisplayName("Test 3 column file")
    void testThreeColumnFileTrain(){
        assertThrows(IllegalArgumentException.class, () -> linearRegression.train(CSVReader.readTable("CSVFiles/ThreeFile.csv")));
    }

    @Test
    @DisplayName("Test miles_dollars train")
    void testMilesDollarsTrain(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertAll(
                () -> assertEquals(1.255618003713406, linearRegression.getSlope()),
                () -> assertEquals(273.9464336390929, linearRegression.getOrigin()),
                () -> assertEquals(71.1003965489222, linearRegression.getError())
        );
    }

    @Test
    @DisplayName("Test numbers train")
    void testNumbersTrain(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertAll(
                () -> assertEquals(1.9999999999999996, linearRegression.getSlope()),
                () -> assertEquals(8.881784197001252E-16, linearRegression.getOrigin()),
                () -> assertEquals(1.118033988749895, linearRegression.getError())
        );
    }

    ///Estimate
    @Test
    @DisplayName("Estimate 1 miles_dollars")
    void estimateOneMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertArrayEquals(new Double[]{204.10165509388406, 346.3024481917285}, linearRegression.estimate(1.));
    }

    @Test
    @DisplayName("Estimate 5 miles_dollars")
    void estimateFiveMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertArrayEquals(new Double[]{209.1241271087377, 351.32492020658214}, linearRegression.estimate(5.));
    }

    @Test
    @DisplayName("Estimate 23 miles_dollars")
    void estimateTwentyThreeMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertArrayEquals(new Double[]{231.725251175579, 373.92604427342343}, linearRegression.estimate(23.));
    }

    @Test
    @DisplayName("Estimate 0.15 numbers")
    void estimateDotFiveTeenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertArrayEquals(new Double[]{-0.8180339887498941, 1.4180339887498956}, linearRegression.estimate(.15));
    }

    @Test
    @DisplayName("Estimate 7 numbers")
    void estimateSevenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertArrayEquals(new Double[]{12.881966011250102, 15.118033988749891}, linearRegression.estimate(7.));
    }

    @Test
    @DisplayName("Estimate 11 numbers")
    void estimateElevenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertArrayEquals(new Double[]{20.8819660112501, 23.118033988749893}, linearRegression.estimate(11.));
    }
}