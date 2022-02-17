import java.util.List;

public class Row implements RowInterface{
    private String data;

    public Row(String data){
        this.data = data;
    }

    @Override
    public String getData() {
        return data;
    }

    public void setData(String data){
        this.data = data;
    }
}
