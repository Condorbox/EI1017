package MVC;

import MVC.Controller.Controller;
import MVC.Model.Model;
import MVC.View.View;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        View viewKnn = new View(primaryStage);
        Controller controller = new Controller();
        Model model = new Model();

        model.setView(viewKnn);
        controller.setModel(model);
        controller.setView(viewKnn);
        viewKnn.setController(controller);
        viewKnn.setModel(model);

        viewKnn.createGUI();
    }
}
