package MVC.Controller;

import MVC.Model.IModelController;
import MVC.View.IViewController;

import java.util.LinkedList;
import java.util.List;

public class Controller implements IController {
    private IModelController model;
    private IViewController view;

    public Controller(){}

    @Override
    public void setModel(IModelController model) {
        this.model = model;
    }
    @Override
    public void setView(IViewController view){
        this.view = view;
    }

    @Override
    public void updateFile() {
        model.setFile(view.getFile());
    }

    @Override
    public void setDistance() {
        model.setDistance(view.getDistance());
    }

    @Override
    public void estimateNewPoint() {
        String[] coordinates =  view.getNewPoint().split(",");
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
