package CSV;

import java.util.List;

public class RowWithLabel extends Row implements RowWithLabelInterface{
    private String label;

    public RowWithLabel(){
        super();
        this.label = "";
    }

    public RowWithLabel(String label, List<Double> data) {
        super(data);
        this.label = label;
    }
    
    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }
}
