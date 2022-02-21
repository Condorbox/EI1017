import java.util.ArrayList;
import java.util.List;

public class Row implements RowInterface{
    private List<Double> data;

    public Row(){
        data = new ArrayList<>();
    }

    public Row(List<Double> data){
        this.data = data;
    }

    @Override
    public List<Double> getData() {
        return data;
    }

    @Override
    public void addData(Double data){
        this.data.add(data);
    }
}
