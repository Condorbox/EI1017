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

    public List<String> getLabelColumn(){
        List<String> labelColumn = new ArrayList<>();
        for (RowWithLabel row : dataTable){
            labelColumn.add(row.getLabel());
        }
        return labelColumn;
    }
}
