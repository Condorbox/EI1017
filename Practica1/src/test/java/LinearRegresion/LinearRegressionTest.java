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
                () -> assertEquals(273.9464336390929, linearRegression.getOrigin())
        );
    }

    @Test
    @DisplayName("Test numbers train")
    void testNumbersTrain(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertAll(
                () -> assertEquals(1.9999999999999996, linearRegression.getSlope()),
                () -> assertEquals(8.881784197001252E-16, linearRegression.getOrigin())
        );
    }

    @Test
    @DisplayName("Estimate 1 miles_dollars")
    void estimateOneMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertEquals(275.2020516428063, linearRegression.estimate(1.));
    }

    @Test
    @DisplayName("Estimate 5 miles_dollars")
    void estimateFiveMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertEquals(280.2245236576599, linearRegression.estimate(5.));
    }

    @Test
    @DisplayName("Estimate 23 miles_dollars")
    void estimateTwentyThreeMilesDollars(){
        linearRegression.train(CSVReader.readTable("CSVFiles/miles_dollars.csv"));
        assertEquals(302.8256477245012, linearRegression.estimate(23.));
    }

    @Test
    @DisplayName("Estimate 0.15 numbers")
    void estimateDotFiveTeenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertEquals(.3000000000000008, linearRegression.estimate(.15));
    }

    @Test
    @DisplayName("Estimate 7 numbers")
    void estimateSevenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertEquals(13.999999999999996, linearRegression.estimate(7.));
    }

    @Test
    @DisplayName("Estimate 11 numbers")
    void estimateElevenNumbers(){
        linearRegression.train(CSVReader.readTable("CSVFiles/numbers.csv"));
        assertEquals(21.999999999999996, linearRegression.estimate(11.));
    }
}