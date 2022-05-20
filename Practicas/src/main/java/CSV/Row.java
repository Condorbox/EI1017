package CSV;

import java.util.ArrayList;
import java.util.List;

public class Row {
    protected List<Double> data;

    public Row(){
        data = new ArrayList<>();
    }

    public Row(List<Double> data){
        this.data = data;
    }

    public List<Double> getData() {
        return data;
    }

    public void addData(Double data){
        this.data.add(data);
    }
}
