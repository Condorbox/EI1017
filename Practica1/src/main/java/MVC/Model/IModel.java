package MVC.Model;

import MVC.View.IView;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface IModel {
    void setFile(File file);
    void setView(IView view);
    List<String> getHeader();
    Map<String, List<List<Double>>> getPoints();
    void setDistance(int distanceIndex);
    void estimateNewPoint(List<Double> newPoint);
    void saveFile();
}
