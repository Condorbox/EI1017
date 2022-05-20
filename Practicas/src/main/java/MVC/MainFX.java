package MVC;

import MVC.Controller.Controller;
import MVC.Model.Model;
import MVC.View.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pr 4 EI1017");

        View viewKnn = new View(primaryStage);
        Controller controller = new Controller();
        Model model = new Model();

        model.setView(viewKnn);
        controller.setModel(model);
        controller.setView(viewKnn);
        viewKnn.setController(controller);
        viewKnn.setModel(model);

        StackPane root = new StackPane();

        root.getChildren().add(createTabPane(viewKnn.createGUI()));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TabPane createTabPane(Tab tabKnn){
        TabPane tabPane = new TabPane();
        tabPane.getTabs().add(tabKnn);
        tabPane.getSelectionModel().select(0); //Default Selected

        return tabPane;
    }
}
