package MVC.Controller;

import MVC.Model.IModelController;
import MVC.View.IViewController;
import javafx.stage.Stage;

public interface IController {
    void updateFile(Stage stage);
    void setModel(IModelController model);
    void setView(IViewController view);
    void changeGraphic(int xIndex, int yIndex);
    void setDistance(int distanceIndex);
    void estimateNewPoint(String newPoint);
    void saveFile();
    void showLegend(boolean show);
}
