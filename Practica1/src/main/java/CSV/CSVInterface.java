package CSV;

public interface CSVInterface {
    Table<Row> readTable(String csvFile);

    TableWithLabel readTableWithLabels(String csvFileWithLabels);
}
