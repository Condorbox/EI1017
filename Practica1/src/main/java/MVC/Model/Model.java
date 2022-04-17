package MVC.Model;

import AI.KNN.KNN;
import CSV.CSV;
import MVC.View.IView;
import Patterns.FactoryPattern.DistanceType;

import java.io.File;
import java.util.*;

public class Model implements IModel {
    private IView view;
    private final KNN knn;
    private final CSV CSVReader;

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
            putPoint(entry.getValue(), entry.getKey());
        }
    }

    private void putPoint(String key, List<Double> value){
        if (points.containsKey(key)){
            points.get(key).add(value);
        }else{
            List<List<Double>> newPoint = new LinkedList<>();
            newPoint.add(value);
            points.put(key, newPoint);
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

    @Override
    public void setDistance(int distanceIndex) {
        knn.setDistance(DistanceType.indexToDistance(distanceIndex));
    }

    @Override
    public void estimateNewPoint(List<Double> newPoint) {
        String label = knn.estimate(newPoint);
        if (alreadyContainsPoint(label, newPoint)){
            label = "Already contains this point";
        }else{
            putPoint(label, newPoint);
            view.chartNewPoint(label, newPoint);
        }
        view.updateEstimateLabel(label);

    }

    private boolean alreadyContainsPoint(String label, List<Double> newPoint){
        boolean contains = false;
        for (List<Double> value : points.get(label)) {
            contains = value.equals(newPoint);
            if (contains)
                break;
        }
        return contains;
    }
}
