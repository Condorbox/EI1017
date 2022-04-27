package MVC.Controller;

import MVC.Model.IModelController;

import java.io.File;

public interface IController {
    void updateFile(File file);
    void setModel(IModelController model);
    void setDistance(int distanceIndex);
    void estimateNewPoint(String newPoint);
    void saveFile();
}
