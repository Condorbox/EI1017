import java.util.ArrayList;
import java.util.List;

public class Table implements TableInterface{
    private List<String> headers;
    private List<Row> dataTable;

    public Table(){
        this.headers = new ArrayList<>();
        this.dataTable = new ArrayList<>();
    }

    public Table(List<String> headers, List<Row> dataTable){
        this.headers = headers;
        this.dataTable = dataTable;
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

    @Override
    public void addHeader(String header){
        this.headers.add(header);
    }

    @Override
    public void addRow(Row row){
        dataTable.add(row);
    }

    @Override
    public List<String> getHeaders(){
        return headers;
    }

    @Override
    public List<Row> getDataTable(){
        return dataTable;
    }
}
