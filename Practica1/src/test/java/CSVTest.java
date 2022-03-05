import CSV.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {
    CSVTest() throws URISyntaxException {
    }

    private static CSV csv;
    private static Table<Row> emptyTable;
    private static TableWithLabel emptyTableWithLabel;

    /*private final String crashCatalonia = "src/main/resources/CSVFiles/crash_catalonia.csv";
    //private final String emptyFile = "src/main/resources/CSVFiles/EmptyFile.csv";
    //private final String fileOnlyHeaderAndLabel = "src/main/resources/CSVFiles/FileOnlyHeaderAndLabel.csv";
    //private final String fileWithOnlyHeaders = "src/main/resources/CSVFiles/FileWithOnlyHeaders.csv";
    //private final String numbers = "src/main/resources/CSVFiles/numbers.csv";
    private final String numbersWithLabels = "src/main/resources/CSVFiles/numbersWithLabels.csv";*/

    private final String crashCatalonia = Paths.get(getClass().getResource("CSVFiles/crash_catalonia.csv").toURI()).toFile().getAbsolutePath();
    private final String emptyFile = Paths.get(getClass().getResource("CSVFiles/EmptyFile.csv").toURI()).toFile().getAbsolutePath();
    private final String fileOnlyHeaderAndLabel = Paths.get(getClass().getResource("CSVFiles/FileOnlyHeaderAndLabel.csv").toURI()).toFile().getAbsolutePath();
    private final String fileWithOnlyHeaders = Paths.get(getClass().getResource("CSVFiles/FileWithOnlyHeaders.csv").toURI()).toFile().getAbsolutePath();
    private String numbers = Paths.get(getClass().getResource("CSVFiles/numbers.csv").toURI()).toFile().getAbsolutePath();
    private final String numbersWithLabels = Paths.get(getClass().getResource("CSVFiles/numbersWithLabels.csv").toURI()).toFile().getAbsolutePath();


    @BeforeAll
    static void initAll(){
        csv = new CSV(",");
        emptyTable = new Table<Row>();
        emptyTableWithLabel = new TableWithLabel();
    }

    ///CSV without labels
    @Test
    @DisplayName("Read file doesn't exit")
    void testReadFileDoesNotExit() {
        Table<Row> table = csv.readTable("File.csv");
        assertAll("Test read null file",
                () -> assertArrayEquals(emptyTable.getHeaders().toArray(), table.getHeaders().toArray()),
                () -> assertTrue(compareRows(emptyTable, table))
        );
    }

    @Test
    @DisplayName("File wrong format")
    void testReadFileWrongFormat() {
        Table table = csv.readTable(crashCatalonia);
        assertAll("Test wrong format file",
                () -> assertArrayEquals(emptyTable.getHeaders().toArray(), table.getHeaders().toArray()),
                () -> assertTrue(compareRows(emptyTable, table))
        );
    }

    @Test
    @DisplayName("Test getColumn incorrect index")
    void testGetColumnIncorrectIndex(){
        Table table = csv.readTable(numbers);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> table.getColumnAt(-1));
    }

    @Test
    @DisplayName("Test getColumn")
    void testGetColumn(){
        Table table = csv.readTable(numbers);
        List<Double> column = Arrays.asList(0., 1., 2., 3.);
        assertArrayEquals(column.toArray(), table.getColumnAt(0).toArray());
    }

    @Test
    @DisplayName("Test getRow incorrect index")
    void testGetRowIncorrectIndex(){
        Table table = csv.readTable(numbers);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> table.getRowAt(-1));
    }

    @Test
    @DisplayName("Test getRow")
    void testGetRow(){
        Table table = csv.readTable(numbers);
        Row row = new Row(Arrays.asList(1.,2.));
        assertEquals(row.getData(), table.getRowAt(1).getData());
    }

    @Test
    @DisplayName("Read only headers file")
    void testReadOnlyHeadersFile(){
        Table dataTable = csv.readTable(fileWithOnlyHeaders);
        List<String> headers = Arrays.asList("lenth", "class");
        List<Row> data = new ArrayList<>();
        Table tableCorrect = new Table<Row>(headers, data);

        assertAll("Test read only headers file",
                () -> assertArrayEquals(tableCorrect.getHeaders().toArray(), dataTable.getHeaders().toArray()),
                () -> assertTrue(compareRows(tableCorrect, dataTable))
        );
    }

    @Test
    @DisplayName("Read file")
    void testReadFile(){
        Table dataTable = csv.readTable(numbers);
        List<String> headers = Arrays.asList("code", "number");
        List<Row> data = createRows();
        Table tableCorrect = new Table<Row>(headers, data);

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
        assertAll("Test read null file",
                () -> assertArrayEquals(emptyTableWithLabel.getHeaders().toArray(), table.getHeaders().toArray()),
                () -> assertTrue(compareRowsWithLabels(emptyTableWithLabel, table))
        );
    }

    @Test
    @DisplayName("File wrong format")
    void testReadFileWithLabelWrongFormat() {
        TableWithLabel table = csv.readTableWithLabels(crashCatalonia);
        assertAll("Test wrong format file",
                () -> assertArrayEquals(emptyTableWithLabel.getHeaders().toArray(), table.getHeaders().toArray()),
                () -> assertTrue(compareRowsWithLabels(emptyTableWithLabel, table))
        );
    }

    @Test
    @DisplayName("Test getColumn with label incorrect index")
    void testGetColumnWithLabelIncorrectIndex(){
        TableWithLabel table = csv.readTableWithLabels(numbersWithLabels);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> table.getColumnAt(-1));
    }

    @Test
    @DisplayName("Test getColumn with label")
    void testGetColumnWithLabel(){
        TableWithLabel table = csv.readTableWithLabels(numbersWithLabels);
        List<Double> column = Arrays.asList(0., 1., 2., 3.);
        assertArrayEquals(column.toArray(), table.getColumnAt(0).toArray());
    }

    @Test
    @DisplayName("Read null file with label")
    void testReadNullFileWithLabel(){
        TableWithLabel dataTable = csv.readTableWithLabels(emptyFile);
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
        TableWithLabel dataTable = csv.readTableWithLabels(fileWithOnlyHeaders);
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
        TableWithLabel dataTable = csv.readTableWithLabels(fileOnlyHeaderAndLabel);
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
        TableWithLabel dataTable = csv.readTableWithLabels(numbersWithLabels);
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