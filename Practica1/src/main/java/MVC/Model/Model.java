package MVC.Model;

import AI.KNN.KNN;
import CSV.CSV;
import CSV.TableWithLabel;
import MVC.View.IViewModel;
import Patterns.FactoryPattern.DistanceType;

import java.io.File;
import java.util.*;

public class Model implements IModelController, IModelView {
    private IViewModel view;
    private final KNN knn;
    private final CSV CSVReader;

    private Map<String, List<List<Double>>> points;
    private Map<List<Double>, String> dataNotSaved;
    private List<String> header;

    private boolean firstEstimated = true;
    private File file;
    private int pointSize;

    public Model(){
        this.knn = new KNN();
        this.CSVReader = new CSV();
    }

    @Override
    public void setFile(File file) {
        if(file == null) {throw new NullPointerException("File not selected");}

        TableWithLabel knnTable = CSVReader.readTableWithLabels(file.getAbsolutePath());
        if (knnTable.getDataTable().size() == 0 && knnTable.getHeaders().size() == 0){
            view.errorMessage("Invalid file", "must select a valid file");
            throw new IllegalArgumentException("Must select a valid file");
        }
        knn.train(knnTable);
        dataNotSaved = new HashMap<>();
        firstEstimated = true;
        this.file = file;
        header = knn.getHeader().subList(0, knn.getHeader().size() - 1);

        Optional<Map.Entry<List<Double>, String>> opPointSize = knn.getDataTable().entrySet().stream().findFirst();
        opPointSize.ifPresent(listStringEntry -> pointSize = listStringEntry.getKey().size());

        updatePoints();

        view.updateFile(file.getName());
    }


    @Override
    public void setView(IViewModel view) {
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
        fileNotSelectedError();

        if(newPoint.size() != pointSize){
            view.errorMessage("Incorrect Point","Example Point: " + examplePoint());
            throw new IllegalArgumentException("New Point size must be " + pointSize);
        }

        String label = knn.estimate(newPoint);
        if (!alreadyContainsPoint(newPoint)){
            putPoint("New Point", newPoint);
            view.chartNewPoint(label, newPoint, firstEstimated);
        }

        dataNotSaved.put(newPoint, label);
        firstEstimated = false;
        view.labelFileNotSaved(file.getName());
        view.updateEstimateLabel(label);
    }

    private String examplePoint(){
        StringBuilder examplePoint = new StringBuilder();
        for (int i = 0; i < pointSize; i++){
            examplePoint.append(i);
            if(i != pointSize - 1 )
                examplePoint.append(",");
        }

        return examplePoint.toString();
    }

    private boolean alreadyContainsPoint(List<Double> newPoint){
        boolean contains = false;
        if (!points.containsKey("New Point")) return false;
        for (List<Double> value : points.get("New Point")) {
            contains = value.equals(newPoint);
            if (contains)
                break;
        }
        return contains;
    }

    @Override
    public void saveFile() {
        fileNotSelectedError();
        WriteFile.saveFile(file, dataNotSaved);
        dataNotSaved.clear();
        view.labelFileSaved(file.getName());
    }

    private void fileNotSelectedError(){
        if (file == null){
            view.errorMessage("File Not Selected", "You must select a correct file");
            throw new UnsupportedOperationException("Must be a correct selected file");
        }
    }


}
