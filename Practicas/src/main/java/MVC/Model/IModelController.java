package MVC.Model;

import MVC.View.IViewModel;

import java.io.File;
import java.util.List;

public interface IModelController {
    void setFile(File file);
    void setView(IViewModel view);
    void setDistance(int distanceIndex);
    void estimateNewPoint(List<Double> newPoint);
    void saveFile();
}
