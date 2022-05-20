package CSV;

import java.util.ArrayList;
import java.util.List;

public class Table<T extends Row> {
    protected List<String> headers;
    protected List<T> dataTable;

    public Table(){
        this.headers = new ArrayList<>();
        this.dataTable = new ArrayList<>();
    }

    public Table(List<String> headers, List<T> dataTable){
        this.headers = headers;
        this.dataTable = dataTable;
    }

    public T getRowAt(int index) {
        if(index >= dataTable.size() || index < 0)
            throw new ArrayIndexOutOfBoundsException("index too big or too small, must be between [0," + (dataTable.size()-1) + "]");
        return dataTable.get(index);
    }

    public List<Double> getColumnAt(int index) {
        if (index >= headers.size() || index < 0)
            throw new ArrayIndexOutOfBoundsException("index too big or too small, must be between [0," + (headers.size()-1) + "]");
        List<Double> column = new ArrayList<>();
        for (T row : dataTable){
            column.add(row.getData().get(index));
        }
        return column;
    }

    public void addHeader(String header){
        this.headers.add(header);
    }

    public void addRow(T row){
        dataTable.add(row);
    }

    public List<String> getHeaders(){
        return headers;
    }

    public List<T> getDataTable(){
        return dataTable;
    }
}
