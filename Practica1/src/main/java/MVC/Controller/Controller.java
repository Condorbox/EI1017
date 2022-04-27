package MVC.Controller;

import MVC.Model.IModelController;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Controller implements IController {
    private IModelController model;

    public Controller(){}

    @Override
    public void updateFile(File file) {
        model.setFile(file);
    }

    @Override
    public void setModel(IModelController model) {
        this.model = model;
    }

    @Override
    public void setDistance(int distanceIndex) {
        model.setDistance(distanceIndex);
    }

    @Override
    public void estimateNewPoint(String newPoint) {
        String[] coordinates =  newPoint.split(",");
        List<Double> estimateCoordinates = new LinkedList<>();
        try {
            for (String coordinate: coordinates) {
                estimateCoordinates.add(Double.parseDouble(coordinate));
            }
        }catch (Exception exception){
            exception.fillInStackTrace();
        }
        model.estimateNewPoint(estimateCoordinates);
    }

    @Override
    public void saveFile() {
        model.saveFile();
    }
}
