package MVC.View;

import MVC.Controller.IController;
import MVC.Model.IModel;

import java.util.List;
import java.util.Map;

public interface IView {
    void updateFile(String name, Map<List<Double>,String> dataTable);
    void createGUI();
    void setController(IController controller);
    void setModel(IModel model);
    void updateChart(int xIndex, int yIndex);
}
