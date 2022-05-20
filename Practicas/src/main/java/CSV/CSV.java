package CSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSV {
    private static final Table<Row> TABLE_ERROR = new Table<Row>();
    private static final TableWithLabel TABLE_WITH_LABEL_ERROR = new TableWithLabel();
    private final String delimiter;

    public CSV() {
        this.delimiter = ",";
    }

    public CSV(String delimiter) {
        this.delimiter = delimiter;
    }

    public Table<Row> readTable(String csvFile) {
        Table<Row> table = new Table<Row>();
        table = readCSV(csvFile, table, false);
        if (table == null)
            return TABLE_ERROR;
        return table;
    }

    public TableWithLabel readTableWithLabels(String csvFileWithLabels) {
        TableWithLabel table = new TableWithLabel();
        table = readCSV(csvFileWithLabels, table, true);
        if (table == null)
            return TABLE_WITH_LABEL_ERROR;
        return table;
    }

    private <T extends Table> T readCSV(String csvFile, T table, boolean withLabels) {
        try {
            Scanner myReader = new Scanner(new File(csvFile));
            boolean headersRead = false;
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split(delimiter);
                if (!headersRead) { //Header
                    for (String header : line) {
                        table.addHeader(header);
                    }
                    headersRead = true;
                } else {
                    RowWithLabel newRow;
                    try {
                        newRow = lineToRow(line, table.getHeaders().size() - 1, withLabels);
                    }catch (NumberFormatException exception){
                        System.out.println("Wrong csv format");
                        exception.printStackTrace();
                        return null;
                    }
                    table.addRow(newRow);
                }
            }
            return table;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return null;
        }
    }

    private RowWithLabel lineToRow(String[] line, int labelLine, boolean withLabels) throws NumberFormatException {
        RowWithLabel newRow = new RowWithLabel();
        for (int i = 0; i < line.length; i++) {
            if (i == labelLine && withLabels) { //Label
                newRow.setLabel(line[i]);
            } else {
                Double dataToAdd = Double.parseDouble(line[i]);
                newRow.addData(dataToAdd);
            }
        }
        return newRow;
    }
}
