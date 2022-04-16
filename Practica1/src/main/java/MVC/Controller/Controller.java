package MVC.Controller;

import MVC.Model.IModel;
import MVC.View.IView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements IController {
    private IModel model;
    private IView view;

    private FileChooser fileChooser;

    public Controller(){
        fileChooser = new FileChooser();
    }

    @Override
    public void updateFile(Stage stage) {
        model.setFile(fileChooser.showOpenDialog(stage));
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void changeXGraphic(int xIndex, int yIndex) {
        if(xIndex >= 0 && yIndex >= 0){
            view.updateChart(xIndex, yIndex);
        }
    }
}
