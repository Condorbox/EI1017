import java.util.ArrayList;
import java.util.List;

public class Table implements TableInterface{
    private List<String> headers;
    private List<Row> dataTable;

    public Table(){
        this.headers = new ArrayList<>();
        this.dataTable = new ArrayList<>();
    }

    @Override
    public Row getRowAt(int index) {
        if(index >= dataTable.size() || index < 0)
            throw new ArrayIndexOutOfBoundsException("index too big or too small, must be between [0," + (dataTable.size()-1) + "]");
        return dataTable.get(index);
    }

    @Override
    public List<Double> getColumAt(int index) {
        if (index >= headers.size() || index < 0)
            throw new ArrayIndexOutOfBoundsException("index too big or too small, must be between [0," + (headers.size()-1) + "]");
        List<Double> column = new ArrayList<>();
        for (Row row : dataTable){
            column.add(row.getData().get(index));
        }
        return column;
    }

    public void addHeader(String header){
        this.headers.add(header);
    }

    public void addRow(Row element){
        dataTable.add(element);
    }

    public void printTable(int rowIndex, int columnIndex){
        for (String e : headers)
            System.out.print(e + " ");
        for (Row element : dataTable){
            System.out.println();
            element.printRow();
        }

        getRowAt(rowIndex);
        getColumAt(columnIndex);
    }
}
