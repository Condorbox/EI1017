package MVC.View;

import MVC.Controller.IController;
import MVC.Model.IModel;
import javafx.scene.control.Tab;

import java.util.List;

public interface IView {
    void updateFile(String name);
    Tab createGUI();
    void setController(IController controller);
    void setModel(IModel model);
    void updateChart(int xIndex, int yIndex);
    void updateEstimateLabel(String label);
    void chartNewPoint(String label, List<Double> data);
}
