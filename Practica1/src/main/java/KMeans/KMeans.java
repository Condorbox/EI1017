package KMeans;

import CSV.Row;
import CSV.RowWithLabel;
import CSV.Table;

import CSV.TableWithLabel;
import KNN.DistanceData;

import KNN.KNN;
import Kmeans.DurstenfeldAlgorithm;

import Utilities.AlgorithmInterface;
import Utilities.Arithmetic;

import java.util.*;

public class KMeans implements AlgorithmInterface<Table<Row>, Row, String>{//TODO encapsular, test y mejorar :D
     int numberClusters;
     int iterations;
     long seed;
     List<Row> centroid;
     List<List<RowWithLabel>> dataGroup; //TODO change I think map is a better option

    public KMeans(int numberClusters, int iterations, long seed){
        this.numberClusters = numberClusters;
        this.iterations = iterations;
        this.seed = seed;
        dataGroup = new ArrayList<>(numberClusters);
    }

    public KMeans(){
        this.numberClusters = 3;
        this.iterations = 3;
        this.seed = System.currentTimeMillis();
        dataGroup = new ArrayList<>(numberClusters);
    }

    @Override
    public void train(Table<Row> data) {
        Random random = new Random(seed);
        centroid = DurstenfeldAlgorithm.pickNRandomElements(data.getDataTable(), numberClusters, random); //1
        for (int i = 0; i<iterations; i++){//5
            choseGroup(data.getDataTable(), centroid); //2-3
            centroid = centroid(); //4
        }
    }

    private void choseGroup(List<Row> dataTable, List<Row> randomElements){
        for (Row data : dataTable){
            DistanceData distanceData = new DistanceData();
            for (int i = 0; i<randomElements.size(); i++){
                double distance = Arithmetic.euclideanDistance(randomElements.get(i).getData(), data.getData());//2
                if (distanceData.getType() == null || distanceData.getDistance() < distance){
                    distanceData.setDistance(distance);
                    distanceData.setType(String.valueOf(i));
                }
            }
            int type = Integer.parseInt(distanceData.getType());
            RowWithLabel newData = new RowWithLabel("Cluster-"+type, data.getData());
            this.dataGroup.get(type).add(newData);
        }
    }

    private List<Row> centroid(){
        List<Row> centroids = new ArrayList<>();
        for (List<RowWithLabel> cluster: dataGroup){
            List<Double> centroidData = new ArrayList<>();
            for (int i = 0; i<cluster.get(0).getData().size(); i++){
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
        /*DistanceData distanceData = new DistanceData();
        for (int i = 0; i<centroid.size(); i++){
            double distance = Arithmetic.euclideanDistance(centroid.get(i).getData(), sample.getData());//2
            if (distanceData.getType() == null || distanceData.getDistance() < distance){
                distanceData.setDistance(distance);
                distanceData.setType(String.valueOf(i));
            }
        }
        return "Cluster-"+distanceData.getType();*/ //With mean
        
        List<RowWithLabel> dataTable = new ArrayList<>();
        for (List<RowWithLabel> group: dataGroup) {
            dataTable.addAll(group);
        }
        KNN knn = new KNN();
        knn.train(new TableWithLabel(new ArrayList<>(), dataTable));
        return knn.estimate(sample.getData());
    }
}
