package MVC;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        StackPane root = new StackPane();

        Button btn = new Button("Hola");

        TabPane tabPane = new TabPane();

        Tab tabKnn = new Tab("KNN");
        tabKnn.setClosable(false);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(btn);
        tabKnn.setContent(vBox);

        tabPane.getTabs().add(tabKnn);
        root.getChildren().add(tabPane);
        tabPane.getSelectionModel().select(0); //Default Selected

        primaryStage.setScene(new Scene(root, 250, 250));
        primaryStage.show();
    }
}
