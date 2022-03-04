import CSV.CSV;
import LinearRegresion.LinearRegression;
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

    ///Estimate
    @Test
    @DisplayName("Estimate 10 miles_dollars")
    void estimateTenMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertEquals(286.502613676227, linearRegression.estimate(10.));
    }

    @Test
    @DisplayName("Estimate 2 numbers")
    void estimateTwoNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertEquals(4.0, linearRegression.estimate(2.));
    }

    ///Estimate With Error
    @Test
    @DisplayName("Estimate with error 1 miles_dollars")
    void estimateWithErrorOneMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertArrayEquals(new Double[]{-80.29993110180476, 630.7040343874173}, linearRegression.estimateWithError(1.));
    }

    @Test
    @DisplayName("Estimate with error 5 miles_dollars")
    void estimateWithErrorFiveMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertArrayEquals(new Double[]{-75.27745908695113, 635.726506402271}, linearRegression.estimateWithError(5.));
    }

    @Test
    @DisplayName("Estimate with error 23 miles_dollars")
    void estimateWithErrorTwentyThreeMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertArrayEquals(new Double[]{-52.67633502010983, 658.3276304691123}, linearRegression.estimateWithError(23.));
    }

    @Test
    @DisplayName("Estimate with error 0.15 numbers")
    void estimateWithErrorDotFiveTeenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertArrayEquals(new Double[]{-1.936067977499789, 2.5360679774997905}, linearRegression.estimateWithError(.15));
    }

    @Test
    @DisplayName("Estimate with error 7 numbers")
    void estimateWithErrorSevenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertArrayEquals(new Double[]{11.763932022500207, 16.236067977499786}, linearRegression.estimateWithError(7.));
    }

    @Test
    @DisplayName("Estimate with error 11 numbers")
    void estimateWithErrorElevenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertArrayEquals(new Double[]{19.763932022500207, 24.236067977499786}, linearRegression.estimateWithError(11.));
    }
}