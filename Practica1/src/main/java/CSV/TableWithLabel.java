package CSV;

import java.util.ArrayList;
import java.util.List;

public class TableWithLabel extends Table<RowWithLabel> {

    public TableWithLabel(){
        super();
    }

    public TableWithLabel(List<String> headers, List<RowWithLabel> data) {
        super(headers, data);
    }

    /*@Override
    public List<Double> getColumnAt(int index) {
        if (index > headers.size() || index < 0)
            throw new ArrayIndexOutOfBoundsException("index too big or too small, must be between [0," + (headers.size()-2) + "], label column with getLabelColumn()");
        List<Double> column = new ArrayList<>();
        for (Row row : dataTable){
            column.add(row.getData().get(index));
        }
        return column;
    }*/

    public List<String> getLabelColumn(){
        List<String> labelColumn = new ArrayList<>();
        for (RowWithLabel row : dataTable){
            labelColumn.add(row.getLabel());
        }
        return labelColumn;
    }
}
