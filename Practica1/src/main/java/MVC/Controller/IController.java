package MVC.Controller;

import MVC.Model.IModel;
import MVC.View.IView;
import javafx.stage.Stage;

public interface IController {
    void updateFile(Stage stage);
    void setModel(IModel model);
    void setView(IView view);
    void changeXGraphic(int xIndex, int yIndex);
    void setDistance(int distanceIndex);
    void estimateNewPoint(String newPoint);
}
