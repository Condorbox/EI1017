package AI.KMeans;

import CSV.Row;
import CSV.RowWithLabel;
import CSV.Table;
import CSV.TableWithLabel;

import AI.DistanceClient;
import AI.KNN.DistanceData;
import AI.KNN.KNN;

import Patterns.StrategyPattern.Distance;

import Patterns.StrategyPattern.EuclideanDistance;

import Utilities.AlgorithmInterface;

import java.util.*;

public class KMeans implements AlgorithmInterface<Table<Row>, Row, String>, DistanceClient {
    private final int numberClusters;
    private final int iterations;
    private final long seed;
    private KMeansEstimateType estimateType;
    private List<Row> centroid;
    private List<List<RowWithLabel>> dataGroup;
    private Distance distance;

    public KMeans(int numberClusters, int iterations, long seed, KMeansEstimateType estimateType, Distance distance){
        this.numberClusters = numberClusters;
        this.iterations = iterations;
        this.seed = seed;
        this.estimateType = estimateType;
        this.distance = distance;
        dataGroup = new ArrayList<>();
    }

    public KMeans(){
        this.numberClusters = 3;
        this.iterations = 3;
        this.seed = System.currentTimeMillis();
        estimateType = KMeansEstimateType.knnType;
        distance = new EuclideanDistance();
        dataGroup = new ArrayList<>();
    }

    @Override
    public void train(Table<Row> data) {
        if (data == null || data.getDataTable().size() <= 0) throw new IllegalArgumentException("Data must have some elements");
        if (data.getDataTable().get(0).getData().size() != numberClusters) throw new UnsupportedOperationException("number of cluster and the size of data of each row must be equals");
        Random random = new Random(seed);
        centroid = DurstenfeldAlgorithm.pickNRandomElements(data.getDataTable(), numberClusters, random); //Step 1

        for (int i = 0; i<iterations; i++){ //Step 5
            dataGroup = createGroup(data.getDataTable()); //Step 2-3
            centroid = centroid(); //Step 4
        }
    }

    private List<List<RowWithLabel>> createGroup(List<Row> dataTable){ //Step 3
        List<List<RowWithLabel>> newDataGroup = new ArrayList<>();
        for (Row data : dataTable) {
            DistanceData distanceData = choseGroup(data.getData());
            int type = Integer.parseInt(distanceData.getType());
            RowWithLabel newData = new RowWithLabel("Cluster-" + (type + 1), data.getData());
            addNewData(newDataGroup, type, newData);
        }
        return  newDataGroup;
    }

    private DistanceData choseGroup(List<Double> data){ //Step 2
        DistanceData distanceData = new DistanceData();
        for (int i = 0; i<centroid.size(); i++){
            double distanceNumber = distance.calculateDistance(centroid.get(i).getData(), data);
            if (distanceData.getType() == null || distanceData.getDistance() >= distanceNumber){
                distanceData.setDistance(distanceNumber);
                distanceData.setType(String.valueOf(i));
            }
        }
        return distanceData;
    }

    private void addNewData(List<List<RowWithLabel>> dataList, int index, RowWithLabel newData){
        List<RowWithLabel> groupType;
        try {
            groupType = dataList.get(index);
            groupType.add(newData);
        } catch (IndexOutOfBoundsException e) {
            dataList.add(new ArrayList<>());
            addNewData(dataList, index, newData);
        }
    }

    private List<Row> centroid(){ //Step 4
        List<Row> centroids = new ArrayList<>();
        for (List<RowWithLabel> cluster: dataGroup){
            List<Double> centroidData = new ArrayList<>();
            for (int i = 0; i<dataGroup.get(0).get(0).getData().size(); i++){
                double sum = 0;
                for (Row row : cluster){
                    sum += row.getData().get(i);
                }
                centroidData.add(sum/cluster.size());
            }
            centroids.add(new RowWithLabel(null, centroidData));
        }
        return centroids;
    }

    @Override
    public String estimate(Row sample) {
        if (sample == null || sample.getData() == null || sample.getData().size() <= 0) throw new IllegalArgumentException("Sample data must have some elements");
        if (sample.getData().size() != numberClusters) throw new UnsupportedOperationException("number of cluster and the size of sample data must be equals");
        return switch (estimateType) {
            case meanType -> estimateMean(sample);
            case knnType -> estimateKnn(sample);
        };
    }

    private String estimateKnn(Row sample){
        List<RowWithLabel> dataTable = new ArrayList<>();
        for (List<RowWithLabel> group: dataGroup) {
            dataTable.addAll(group);
        }
        KNN knn = new KNN();
        knn.setDistance(distance);
        knn.train(new TableWithLabel(new ArrayList<>(), dataTable));
        return knn.estimate(sample.getData());
    }

    private String estimateMean(Row sample){
         DistanceData distanceData = new DistanceData();
        for (int i = 0; i<numberClusters; i++){
            double distanceNumber = distance.calculateDistance(centroid.get(i).getData(), sample.getData());
            if (distanceData.getType() == null || distanceData.getDistance() < distanceNumber){
                distanceData.setDistance(distanceNumber);
                distanceData.setType(String.valueOf(i + 1));
            }
        }
        return "Cluster-" + distanceData.getType();
    }

    public void setEstimateType(KMeansEstimateType estimateType){
        this.estimateType = estimateType;
    }

    @Override
    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}
