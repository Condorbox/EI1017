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
                () -> assertEquals(355.50198274461104, linearRegression.getError())
        );
    }

    @Test
    @DisplayName("Test numbers train")
    void testNumbersTrain(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertAll(
                () -> assertEquals(1.9999999999999996, linearRegression.getSlope()),
                () -> assertEquals(8.881784197001252E-16, linearRegression.getOrigin()),
                () -> assertEquals(2.23606797749979, linearRegression.getError())
        );
    }

    ///Estimate
    @Test
    @DisplayName("Estimate 1 miles_dollars")
    void estimateOneMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertArrayEquals(new Double[]{-80.29993110180476, 630.7040343874173}, linearRegression.estimate(1.));
    }

    @Test
    @DisplayName("Estimate 5 miles_dollars")
    void estimateFiveMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertArrayEquals(new Double[]{-75.27745908695113, 635.726506402271}, linearRegression.estimate(5.));
    }

    @Test
    @DisplayName("Estimate 23 miles_dollars")
    void estimateTwentyThreeMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertArrayEquals(new Double[]{-52.67633502010983, 658.3276304691123}, linearRegression.estimate(23.));
    }

    @Test
    @DisplayName("Estimate 0.15 numbers")
    void estimateDotFiveTeenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertArrayEquals(new Double[]{-1.936067977499789, 2.5360679774997905}, linearRegression.estimate(.15));
    }

    @Test
    @DisplayName("Estimate 7 numbers")
    void estimateSevenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertArrayEquals(new Double[]{11.763932022500207, 16.236067977499786}, linearRegression.estimate(7.));
    }

    @Test
    @DisplayName("Estimate 11 numbers")
    void estimateElevenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertArrayEquals(new Double[]{19.763932022500207, 24.236067977499786}, linearRegression.estimate(11.));
    }
}