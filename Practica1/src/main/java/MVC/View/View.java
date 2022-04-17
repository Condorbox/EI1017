package MVC.View;

import MVC.Controller.IController;
import MVC.Model.IModel;
import Patterns.FactoryPattern.DistanceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public class View implements IView{

    private IController controller;
    private IModel model;

    private Stage stage;
    private Label fileName;
    private ScatterChart scatterChart;
    private ObservableList<String> comparableList;
    private ObservableList<DistanceType> distanceType;
    private ComboBox comboX;
    private ComboBox comboY;
    private ComboBox comboDistance;
    private TextField newPoint;
    private TextField newPointText;
    private Button estimateButton;


    public View(Stage stage){
        this.stage = stage;
    }

    @Override
    public FlowPane createGUI() {
        stage.setTitle("KNN");

        Button btn = new Button("Select a file");
        fileName = new Label("NonSelectedFile");
        scatterChart = initializeChart();
        comboX = new ComboBox(comparableList);
        comboY = new ComboBox(comparableList);

        distanceType = FXCollections.observableArrayList(EnumSet.allOf(DistanceType.class));
        comboDistance = new ComboBox(distanceType);
        comboDistance.getSelectionModel().selectFirst();

        newPoint = new TextField("New Point");
        newPointText = new TextField();
        newPointText.setDisable(true);
        estimateButton = new Button("Estimate");

        VBox newPointVBox = new VBox(comboDistance, newPoint, newPointText, estimateButton);

        btn.setOnAction(actionEvent -> controller.updateFile(stage));
        comboX.setOnAction(actionEvent -> controller.changeXGraphic(comboX.getSelectionModel().getSelectedIndex(), comboY.getSelectionModel().getSelectedIndex()));
        comboY.setOnAction(actionEvent -> controller.changeXGraphic(comboX.getSelectionModel().getSelectedIndex(), comboY.getSelectionModel().getSelectedIndex()));

        comboDistance.setOnAction(actionEvent -> controller.setDistance(comboDistance.getSelectionModel().getSelectedIndex()));
        estimateButton.setOnAction(actionEvent -> controller.estimateNewPoint(newPoint.getText()));

        FlowPane flowPane = new FlowPane(btn, fileName, scatterChart, comboX, comboY, newPointVBox);
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(10));

        return flowPane;
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
        //scatterChart.getStylesheets().add(getClass().getClassLoader().getResource("root.css").toExternalForm()); //TODO arreglar el Stylesheet

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
    }

    @Override
    public void updateEstimateLabel(String label) {
        newPointText.setText(label);
    }

    @Override
    public void chartNewPoint(String label, List<Double> data) {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data<>(data.get(comboX.getSelectionModel().getSelectedIndex()), data.get(comboY.getSelectionModel().getSelectedIndex())));
        series.setName("new Point");
        scatterChart.getData().add(series);
    }
}
