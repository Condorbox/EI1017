package MVC.Controller;

import MVC.Model.IModelController;
import MVC.View.IViewController;

public interface IController {
    void setModel(IModelController model);
    void setView(IViewController view);
    void updateFile();
    void setDistance();
    void estimateNewPoint();
    void saveFile();
}
