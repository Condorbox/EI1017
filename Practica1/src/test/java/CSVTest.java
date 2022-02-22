import CSV.*;

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
        Table table = csv.readTable("CSVFiles/crash_catalonia.csv");
        assertEquals(table, null);
    }

    @Test
    @DisplayName("Test getColumn incorrect index")
    void testGetColumnIncorrectIndex(){
        Table table = csv.readTable("CSVFiles/numbers.csv");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> table.getColumnAt(-1));
    }

    @Test
    @DisplayName("Test getColumn")
    void testGetColumn(){
        Table table = csv.readTable("CSVFiles/numbers.csv");
        List<Double> column = Arrays.asList(0., 1., 2., 3.);
        assertArrayEquals(column.toArray(), table.getColumnAt(0).toArray());
    }

    @Test
    @DisplayName("Test getRow incorrect index")
    void testGetRowIncorrectIndex(){
        Table table = csv.readTable("CSVFiles/numbers.csv");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> table.getRowAt(-1));
    }

    @Test
    @DisplayName("Test getRow")
    void testGetRow(){
        Table table = csv.readTable("CSVFiles/numbers.csv");
        Row row = new Row(Arrays.asList(1.,2.));
        assertEquals(row.getData(), table.getRowAt(1).getData());
    }

    @Test
    @DisplayName("Read null File")
    void testReadNullFile(){
        Table dataTable = csv.readTable("CSVFiles/EmptyFile.csv");
        List<String> headers = new ArrayList<>();
        List<Row> data = new ArrayList<>();
        Table tableCorrect = new Table(headers, data);

        assertAll("Test read null file",
                () -> assertArrayEquals(tableCorrect.getHeaders().toArray(), dataTable.getHeaders().toArray()),
                () -> assertTrue(compareRows(tableCorrect, dataTable))
        );
    }

    @Test
    @DisplayName("Read only headers file")
    void testReadOnlyHeadersFile(){
        Table dataTable = csv.readTable("CSVFiles/FileWithOnlyHeaders.csv");
        List<String> headers = Arrays.asList("lenth", "class");
        List<Row> data = new ArrayList<>();
        Table tableCorrect = new Table(headers, data);

        assertAll("Test read only headers file",
                () -> assertArrayEquals(tableCorrect.getHeaders().toArray(), dataTable.getHeaders().toArray()),
                () -> assertTrue(compareRows(tableCorrect, dataTable))
        );
    }

    @Test
    @DisplayName("Read file")
    void testReadFile(){
        Table dataTable = csv.readTable("CSVFiles/numbers.csv");
        List<String> headers = Arrays.asList("code", "number");
        List<Row> data = createRows();
        Table tableCorrect = new Table(headers, data);

        assertAll("Test read file",
                () -> assertArrayEquals(tableCorrect.getHeaders().toArray(), dataTable.getHeaders().toArray()),
                () -> assertTrue(compareRows(tableCorrect, dataTable))
        );
    }

    ///CSV with labels
    @Test
    @DisplayName("Read file with label doesn't exit")
    void testReadFileWithLabelDoesNotExit() {
        TableWithLabel table = csv.readTableWithLabels("File.csv");
        assertEquals(table, null);
    }

    @Test
    @DisplayName("File wrong format")
    void testReadFileWithLabelWrongFormat() {
        TableWithLabel table = csv.readTableWithLabels("CSVFiles/crash_catalonia.csv");
        assertEquals(table, null);
    }

    @Test
    @DisplayName("Test getColumn with label incorrect index")
    void testGetColumnWithLabelIncorrectIndex(){
        TableWithLabel table = csv.readTableWithLabels("CSVFiles/numbersWithLabels.csv");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> table.getColumnAt(-1));
    }

    @Test
    @DisplayName("Test getColumn with label")
    void testGetColumnWithLabel(){
        TableWithLabel table = csv.readTableWithLabels("CSVFiles/numbersWithLabels.csv");
        List<Double> column = Arrays.asList(0., 1., 2., 3.);
        assertArrayEquals(column.toArray(), table.getColumnAt(0).toArray());
    }

    @Test
    @DisplayName("Read null file with label")
    void testReadNullFileWithLabel(){
        TableWithLabel dataTable = csv.readTableWithLabels("CSVFiles/EmptyFile.csv");
        List<String> headers = new ArrayList<>();
        List<RowWithLabel> data = new ArrayList<>();
        TableWithLabel tableCorrect = new TableWithLabel(headers, data);

        assertAll("Test read null file",
                () -> assertArrayEquals(tableCorrect.getHeaders().toArray(), dataTable.getHeaders().toArray()),
                () -> assertTrue(compareRowsWithLabels(tableCorrect, dataTable))
        );
    }

    @Test
    @DisplayName("Read only headers file with label")
    void testReadOnlyHeadersFileWithLabel(){
        TableWithLabel dataTable = csv.readTableWithLabels("CSVFiles/FileWithOnlyHeaders.csv");
        List<String> headers = Arrays.asList("lenth", "class");
        List<RowWithLabel> data = new ArrayList<>();
        TableWithLabel tableCorrect = new TableWithLabel(headers, data);

        assertAll("Test read only headers file",
                () -> assertArrayEquals(tableCorrect.getHeaders().toArray(), dataTable.getHeaders().toArray()),
                () -> assertTrue(compareRowsWithLabels(tableCorrect, dataTable))
        );
    }

    @Test
    @DisplayName("Read file only with header and label")
    void testReadFileOnlyHeaderAndLabel(){
        TableWithLabel dataTable = csv.readTableWithLabels("CSVFiles/FileOnlyHeaderAndLabel.csv");
        List<String> headers = Arrays.asList("class");
        List<RowWithLabel> data = createRowOnlyLabel();
        TableWithLabel tableCorrect = new TableWithLabel(headers, data);

        assertAll("Test read only header and label file",
                () -> assertArrayEquals(tableCorrect.getHeaders().toArray(), dataTable.getHeaders().toArray()),
                () -> assertTrue(compareRowsWithLabels(tableCorrect, dataTable))
        );
    }

    @Test
    @DisplayName("Read file with labels")
    void testReadFileWithLabel(){
        TableWithLabel dataTable = csv.readTableWithLabels("CSVFiles/numbersWithLabels.csv");
        List<String> headers = Arrays.asList("code", "number", "class");
        List<RowWithLabel> data = createRowsWithLabel();
        TableWithLabel tableCorrect = new TableWithLabel(headers,data);

        assertAll("Test read file withLabel",
                () -> assertArrayEquals(tableCorrect.getHeaders().toArray(), dataTable.getHeaders().toArray()),
                () -> assertTrue(compareRowsWithLabels(tableCorrect, dataTable))
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

    private List<RowWithLabel> createRowsWithLabel() {
        List<RowWithLabel> data = new ArrayList<>();
        for (int i = 0; i<4;i++){
            RowWithLabel row = new RowWithLabel("c"+i, Arrays.asList((double)i, (double)i*2));
            data.add(row);
        }
        return data;
    }

    private List<RowWithLabel> createRowOnlyLabel(){
        List<RowWithLabel> data = new ArrayList<>();
        for (int i = 0; i<4;i++){
            RowWithLabel row = new RowWithLabel();
            row.setLabel("c"+i);
            data.add(row);
        }
        return data;
    }

    /*private boolean compareRows(List<Row> actual, List<Row> expected){
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
    }*/

    private boolean compareRows(Table expected, Table actual){
        if (expected.getHeaders().size() != actual.getHeaders().size()) { return false; }
        for (int i = 0; i<expected.getHeaders().size();i++){
            if(!expected.getColumnAt(i).equals(actual.getColumnAt(i))){
                return false;
            }
        }
        return true;
    }

    private boolean compareRowsWithLabels(TableWithLabel expected, TableWithLabel actual){
        if (expected.getHeaders().size() != actual.getHeaders().size()) { return false; }

        for (int i = 0; i<expected.getHeaders().size() -1;i++){
            if(!expected.getColumnAt(i).equals(actual.getColumnAt(i))){
                return false;
            }
        }

        return expected.getLabelColumn().equals(actual.getLabelColumn());
    }
}