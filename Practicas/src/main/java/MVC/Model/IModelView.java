package MVC.Model;

import java.util.List;
import java.util.Map;

public interface IModelView {
    List<String> getHeader();
    Map<String, List<List<Double>>> getPoints();
}
