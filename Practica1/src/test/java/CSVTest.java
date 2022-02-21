import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {
    private static CSV csv;

    @BeforeAll
    static void initAll(){
        csv = new CSV(",");
    }

    ///CSV without labels
    @Test
    @DisplayName("Read file doesn't exit")
    void testReadFileDoesNotExit() {
        Table table = csv.readTable("File.csv");
        assertEquals(table, null);
    }

    @Test
    @DisplayName("File wrong format")
    void testReadFileWrongFormat() {
        Table table = csv.readTable("crash_catalonia.csv");
        assertEquals(table, null);
    }

    @Test
    @DisplayName("Read file")
    void testReadFile() {
        Table dataTable = csv.readTable("numbers.csv");
        List<String> headers = Arrays.asList("code", "number");
        List<Row> data = createRows();
        Table tableCorrect = new Table(headers, data);

        assertAll("Test read file",
                () -> assertArrayEquals(tableCorrect.getHeaders().toArray(), dataTable.getHeaders().toArray()),
                () -> assertTrue(compareRows(dataTable.getDataTable(), tableCorrect.getDataTable()))
        );
    }

    private List<Row> createRows(){
        List<Row> data = new ArrayList<>();
        for (int i = 0; i<4;i++){
            Row row = new Row(Arrays.asList((double)i, (double)i*2));
            data.add(row);
        }
        return data;
    }

    private boolean compareRows(List<Row> actual, List<Row> expected){
        if (actual.size() != expected.size()) { return false; }
        for (int i = 0; i<actual.size(); i++){
            List<Double> actualData = actual.get(i).getData();
            List<Double> expectedData = expected.get(i).getData();

            for (int j = 0; j<actualData.size(); j++){
                if (!actualData.get(j).equals(expectedData.get(j))){
                    System.out.println("actual[" +i+"]: " + actualData.get(j) + "\nexpected["+i+"]: " + expectedData.get(j));
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    @DisplayName("Test getColumn incorrect index")
    void testGetColumnIncorrectIndex(){
        Table table = csv.readTable("numbers.csv");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> table.getColumAt(-1));
    }

    @Test
    @DisplayName("Test getColumn")
    void testGetColumn(){
        Table table = csv.readTable("numbers.csv");
        List<Double> column = Arrays.asList(0., 1., 2., 3.);
        assertArrayEquals(column.toArray(), table.getColumAt(0).toArray());
    }

    @Test
    @DisplayName("Test getRow incorrect index")
    void testGetRowIncorrectIndex(){
        Table table = csv.readTable("numbers.csv");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> table.getRowAt(-1));
    }

    @Test
    @DisplayName("Test Row")
    void testGetRow(){
        Table table = csv.readTable("numbers.csv");
        Row row = new Row(Arrays.asList(1.,2.));
        assertEquals(row.getData(), table.getRowAt(1).getData());
    }

}