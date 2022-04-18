package MVC.Controller;

import MVC.Model.IModel;
import MVC.View.IView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class Controller implements IController {
    private IModel model;
    private IView view;

    private final FileChooser fileChooser;

    public Controller(){
        fileChooser = new FileChooser();
    }

    @Override
    public void updateFile(Stage stage) {
        model.setFile(fileChooser.showOpenDialog(stage));
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void changeXGraphic(int xIndex, int yIndex) {
        if(xIndex >= 0 && yIndex >= 0){
            view.updateChart(xIndex, yIndex);
        }
    }

    @Override
    public void setDistance(int distanceIndex) {
        model.setDistance(distanceIndex);
    }

    @Override
    public void estimateNewPoint(String newPoint) {
        String[] coordinates =  newPoint.split(",");
        List<Double> estimateCoordinates = new LinkedList<>();
        for (String coordinate: coordinates) {
            estimateCoordinates.add(Double.parseDouble(coordinate));
        }
        model.estimateNewPoint(estimateCoordinates);
    }

    @Override
    public void saveFile() {
        model.saveFile();
    }

    @Override
    public void showLegend(boolean show) {
        view.showLegend(show);
    }
}
