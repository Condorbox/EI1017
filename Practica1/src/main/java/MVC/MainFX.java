package MVC;

import MVC.Controller.Controller;
import MVC.Model.Model;
import MVC.View.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
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

        FlowPane flowPane = viewKnn.createGUI();


        StackPane root = new StackPane();

        TabPane tabPane = new TabPane();
        Tab tabKnn = new Tab("KNN");
        tabKnn.setClosable(false);
        tabKnn.setContent(flowPane);

        tabPane.getTabs().add(tabKnn);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        root.getChildren().add(tabPane);
        tabPane.getSelectionModel().select(0); //Default Selected

        primaryStage.show();
    }
}
