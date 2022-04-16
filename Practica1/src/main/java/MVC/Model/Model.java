package MVC.Model;

import AI.KNN.KNN;
import CSV.CSV;
import MVC.View.IView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model implements IModel {
    private IView view;
    private KNN knn;
    private CSV CSVReader;

    private Map<String, List<List<Double>>> points;
    private List<String> header;

    public Model(){
        this.knn = new KNN();
        this.CSVReader = new CSV();
    }

    @Override
    public void setFile(File file) {
        knn.train(CSVReader.readTableWithLabels(file.getAbsolutePath()));
        header = knn.getHeader().subList(0, knn.getHeader().size() - 1);
        updatePoints();

        view.updateFile(file.getName(), knn.getDataTable());
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    private void updatePoints(){
        points = new HashMap<>();
        for (Map.Entry<List<Double>, String> entry: knn.getDataTable().entrySet()) {
            if(points.containsKey(entry.getValue())){
                points.get(entry.getValue()).add(entry.getKey());
            }else{
                List<List<Double>> newPoint = new ArrayList<>();
                newPoint.add(entry.getKey());
                points.put(entry.getValue(), newPoint);
            }
        }
    }

    @Override
    public List<String> getHeader(){
        return header;
    }

    @Override
    public Map<String, List<List<Double>>> getPoints(){
        return points;
    }
}
