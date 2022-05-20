package MVC.View;

import MVC.Controller.IController;
import MVC.Model.IModelView;
import javafx.scene.control.Tab;

import java.util.List;

public interface IViewModel {
    void updateFile(String name);
    Tab createGUI();
    void setController(IController controller);
    void setModel(IModelView model);
    void updateEstimateLabel(String label);
    void chartNewPoint(String label, List<Double> data, boolean firstEstimated);
    void labelFileNotSaved(String fileName);
    void labelFileSaved(String fileName);
    void errorMessage(String title, String content);
}
