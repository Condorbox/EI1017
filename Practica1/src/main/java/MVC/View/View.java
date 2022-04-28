package MVC.View;

import MVC.Controller.IController;
import MVC.Model.IModelView;
import Patterns.FactoryPattern.DistanceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class View implements IViewModel {

    private IController controller;
    private IModelView model;

    private final Stage stage;
    private FileChooser fileChooser;
    private Label fileNameLabel;
    private ScatterChart<XYChart<Double,Double>,XYChart<Double,Double>> scatterChart;
    private ObservableList<String> comparableList;
    private ComboBox<String> comboX;
    private ComboBox<String> comboY;
    private ComboBox<DistanceType> comboDistance;
    private TextField newPoint;
    private TextField newPointText;

    public View(Stage stage){
        this.stage = stage;
    }

    @Override
    public Tab createGUI() {
        fileChooser = new  FileChooser();

        Button openBtn = new Button("Select a file");
        fileNameLabel = new Label("NonSelectedFile");

        initializeChart();
        comboX = new ComboBox<>(comparableList);
        comboY = new ComboBox<>(comparableList);
        CheckBox showLegend = new CheckBox("Show Legend");
        showLegend.setSelected(false);

        ObservableList<DistanceType> distanceType = FXCollections.observableArrayList(EnumSet.allOf(DistanceType.class));
        comboDistance = new ComboBox<>(distanceType);
        comboDistance.getSelectionModel().selectFirst();

        newPoint = new TextField("New Point");
        newPointText = new TextField();
        newPointText.setDisable(true);
        Button estimateBtn = new Button("Estimate");
        Button saveBtn = new Button("Save");

        openBtn.setOnAction(actionEvent -> controller.updateFile(fileChooser.showOpenDialog(stage)));
        comboX.setOnAction(actionEvent -> updateChart(comboX.getSelectionModel().getSelectedIndex(), comboY.getSelectionModel().getSelectedIndex()));
        comboY.setOnAction(actionEvent -> updateChart(comboX.getSelectionModel().getSelectedIndex(), comboY.getSelectionModel().getSelectedIndex()));
        comboDistance.setOnAction(actionEvent -> controller.setDistance(comboDistance.getSelectionModel().getSelectedIndex()));
        estimateBtn.setOnAction(actionEvent -> controller.estimateNewPoint(newPoint.getText()));
        saveBtn.setOnAction(actionEvent -> controller.saveFile());
        showLegend.setOnAction(actionEvent -> showLegend(showLegend.isSelected()));

        BorderPane visualization = createVisualization(openBtn, estimateBtn, saveBtn, showLegend);

        Tab tabKnn = new Tab("KNN");
        tabKnn.setClosable(false);
        tabKnn.setContent(visualization);

        return tabKnn;
    }

    private FlowPane createFilePane(Button btn){
        FlowPane filePane = new FlowPane(btn, fileNameLabel);
        filePane.setHgap(10);
        filePane.setVgap(10);
        return filePane;
    }

    private VBox createNewPointVBox(Button estimateBtn, Button saveBtn){
        VBox newPointVBox = new VBox(comboDistance, newPoint, newPointText, estimateBtn, saveBtn);
        newPointVBox.setAlignment(Pos.CENTER);
        return newPointVBox;
    }

    private BorderPane createVisualization(Button openBtn, Button estimateBtn, Button saveBtn, CheckBox showLegend){
        BorderPane visualization = new BorderPane();

        visualization.setTop(createFilePane(openBtn));

        VBox comboYVBox = new VBox(comboY);
        comboYVBox.setAlignment(Pos.CENTER);
        visualization.setLeft(comboYVBox);

        visualization.setRight(createNewPointVBox(estimateBtn, saveBtn));

        visualization.setCenter(scatterChart);

        BorderPane otpPane = new BorderPane();
        otpPane.setCenter(comboX);
        otpPane.setRight(showLegend);
        visualization.setBottom(otpPane);

        visualization.setPadding(new Insets(10., 10., 10., 10.));

        return visualization;
    }

    private void initializeChart(){
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("X");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y");
        scatterChart = new ScatterChart(xAxis, yAxis);
        showLegend(false);
        scatterChart.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("style.css")).toExternalForm());
    }

    @Override
    public void updateFile(String name) {
        fileNameLabel.setText(name);
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
    public void setModel(IModelView model) {
        this.model = model;
    }

    public void updateChart(int xIndex, int yIndex){
        if(xIndex >= 0 && yIndex >= 0){
            String xHeader = model.getHeader().get(xIndex);
            String yHeader = model.getHeader().get(yIndex);
            scatterChart.setTitle(xHeader + " vs " + yHeader);
            scatterChart.getXAxis().setLabel(xHeader);
            scatterChart.getYAxis().setLabel(yHeader);
            putPointsOnChart(xIndex, yIndex);
        }
    }

    private void putPointsOnChart(int xIndex, int yIndex){
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
    public void chartNewPoint(String label, List<Double> data, boolean firstEstimated) {
        if (firstEstimated){
            addNewSeriesToChart(data);
        }else{
            XYChart.Series series = scatterChart.getData().get(scatterChart.getData().size() - 1);
            series.getData().add(new XYChart.Data<>(data.get(comboX.getSelectionModel().getSelectedIndex()), data.get(comboY.getSelectionModel().getSelectedIndex())));
        }
    }

    private void addNewSeriesToChart(List<Double> data){
        XYChart.Series series = new XYChart.Series();
        series.setName("new Point");
        series.getData().add(new XYChart.Data<>(data.get(comboX.getSelectionModel().getSelectedIndex()), data.get(comboY.getSelectionModel().getSelectedIndex())));
        scatterChart.getData().add(series);
    }

    @Override
    public void labelFileNotSaved(String fileName) {
        fileNameLabel.setText(fileName + "*");
    }

    @Override
    public void labelFileSaved(String fileName) {
        fileNameLabel.setText(fileName);
    }

    public void showLegend(boolean show) {
        scatterChart.setLegendVisible(show);
    }

    @Override
    public void errorMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
