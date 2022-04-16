package MVC.View;

import MVC.Controller.IController;
import MVC.Model.IModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class View implements IView{

    private IController controller;
    private IModel model;

    private Stage stage;
    private Label fileName;
    private ScatterChart scatterChart;
    private ObservableList<String> comparableList;
    ComboBox comboX;
    ComboBox comboY;

    public View(Stage stage){
        this.stage = stage;
    }

    @Override
    public void createGUI() {
        stage.setTitle("KNN");

        Button btn = new Button("Select a file");
        fileName = new Label("NonSelectedFile");
        scatterChart = initializeChart();
        comboX = new ComboBox(comparableList);
        comboY = new ComboBox(comparableList);

        btn.setOnAction(actionEvent -> controller.updateFile(stage));
        comboX.setOnAction(actionEvent -> controller.changeXGraphic(comboX.getSelectionModel().getSelectedIndex(), comboY.getSelectionModel().getSelectedIndex()));
        comboY.setOnAction(actionEvent -> controller.changeXGraphic(comboX.getSelectionModel().getSelectedIndex(), comboY.getSelectionModel().getSelectedIndex()));

        FlowPane flowPane = new FlowPane(btn, fileName, scatterChart, comboX, comboY);
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(10));

        StackPane root = new StackPane();

        TabPane tabPane = new TabPane();
        Tab tabKnn = new Tab("KNN");
        tabKnn.setClosable(false);
        tabKnn.setContent(flowPane);

        tabPane.getTabs().add(tabKnn);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        root.getChildren().add(tabPane);
        tabPane.getSelectionModel().select(0); //Default Selected

        stage.show();
    }

    @Override
    public void updateFile(String name, Map<List<Double>, String> dataTable) {
        fileName.setText(name);
        updateCombo();
    }

    private void updateCombo(){
        comparableList = FXCollections.observableArrayList(model.getHeader());
        comboX.setItems(comparableList);
        comboY.setItems(comparableList);
        comboX.getSelectionModel().selectFirst();
        comboY.getSelectionModel().selectLast();
    }

    @Override
    public void setController(IController controller) {
        this.controller = controller;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    private ScatterChart initializeChart(){
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        ScatterChart scatterChart = new ScatterChart (xAxis, yAxis);
        scatterChart.getStylesheets().add(getClass().getClassLoader().getResource("root.css").toExternalForm());

        return  scatterChart;
    }

    //TODO Ask if this is correct in the MVC pattern
    @Override
    public void updateChart(int xIndex, int yIndex){
        String xHeader = model.getHeader().get(xIndex);
        String yHeader = model.getHeader().get(yIndex);
        scatterChart.setTitle(xHeader + " vs " + yHeader);
        scatterChart.getXAxis().setLabel(xHeader);
        scatterChart.getYAxis().setLabel(yHeader);

        scatterChart.setData(FXCollections.observableArrayList());

        for (Map.Entry<String, List<List<Double>>> entry: model.getPoints().entrySet()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(entry.getKey());
            for (List<Double> coordinates: entry.getValue()) {
                series.getData().add(new XYChart.Data<>(coordinates.get(xIndex), coordinates.get(yIndex)));
            }
            scatterChart.getData().add(series);
        }

        System.out.println(scatterChart.getStylesheets()); //TODO arreglar el Stylesheet
    }
}
