package MVC.Controller;

import MVC.Model.IModelController;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class Controller implements IController {
    private IModelController model;

    private final FileChooser fileChooser;

    public Controller(){
        fileChooser = new FileChooser();
    }

    @Override
    public void updateFile(Stage stage) {
        model.setFile(fileChooser.showOpenDialog(stage));
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
