package MVC.Controller;

import MVC.Model.IModelController;
import javafx.stage.Stage;

public interface IController {
    void updateFile(Stage stage);
    void setModel(IModelController model);
    void setDistance(int distanceIndex);
    void estimateNewPoint(String newPoint);
    void saveFile();
}
