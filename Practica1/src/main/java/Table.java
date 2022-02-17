import java.util.ArrayList;
import java.util.List;

public class Table implements TableInterface{
    private List<String> headers;
    private List<ArrayList<Row>> matrix;

    public Table(){
        this.headers = new ArrayList<>();
        this.matrix = new ArrayList<ArrayList<Row>>();
    }

    @Override
    public void getRowAt(int index) {

    }

    @Override
    public List<String> getColumAt(int index) {
        List<String> column = new ArrayList<>();
        for (Row elemnt : matrix.get(index))
            column.add(elemnt.getData());
        return column;
    }

    public void addHeader(String header){
        this.headers.add(header);
    }

    public void addColum(){
        matrix.add(new ArrayList<Row>());
    }

    public void addRow(int columnNumber, Row element){
        matrix.get(columnNumber).add(element);
    }

    public void printTable(){
        for(String header : headers)
            System.out.print(header + ", ");
        for (ArrayList<Row> column : matrix){
            System.out.println();
            for(Row e : column)
                System.out.print(e.getData() + ", ");
        }
    }
}
