package Kmeans;

import CSV.Row;
import CSV.RowWithLabel;
import CSV.Table;

import KNN.DistanceData;

import Utilities.AlgorithmInterface;
import Utilities.Arithmetic;

import java.util.*;

public class KMeans implements AlgorithmInterface<Table<RowWithLabel>, Row, String>{//TODO encapsular, test y mejorar :D
     int numberClusters;
     int iterations;
     long seed;
     List<List<RowWithLabel>> clusterGroup;
     List<RowWithLabel> centroid;

    public KMeans(int numberClusters, int iterations, long seed){
        this.numberClusters = numberClusters;
        this.iterations = iterations;
        this.seed = seed;
        clusterGroup = new ArrayList<>(numberClusters);
    }

    public KMeans(){
        this.numberClusters = 3;
        this.iterations = 3;
        this.seed = System.currentTimeMillis();
        clusterGroup = new ArrayList<>(numberClusters);
    }

    @Override
    public void train(Table<RowWithLabel> data) {
        Random random = new Random(seed);
        centroid = DurstenfeldAlgorithm.pickNRandomElements(data.getDataTable(), numberClusters, random); //1
        for (int i = 0; i<iterations; i++){//5
            choseGroup(data.getDataTable(), centroid); //2-3
            centroid = centroid(); //4
        }
    }

    private void choseGroup(List<RowWithLabel> dataTable, List<RowWithLabel> randomElements){
        for (RowWithLabel data : dataTable){
            DistanceData distanceData = new DistanceData();
            for (int i = 0; i<randomElements.size(); i++){
                double distance = Arithmetic.euclideanDistance(randomElements.get(i).getData(), data.getData());//2
                if (distanceData.getType() == null || distanceData.getDistance() < distance){
                    distanceData.setDistance(distance);
                    distanceData.setType(String.valueOf(i));
                }
            }
            int type = Integer.parseInt(distanceData.getType());
            clusterGroup.get(type).add(data);
        }
    }

    private List<RowWithLabel> centroid(){
        List<RowWithLabel> centroids = new ArrayList<>();
        for (List<RowWithLabel> cluster: clusterGroup){
            List<Double> centroidData = new ArrayList<>();
            for (int i = 0; i<cluster.get(0).getData().size(); i++){
                double sum = 0;
                for (RowWithLabel row : cluster){
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
        DistanceData distanceData = new DistanceData();
        for (int i = 0; i<centroid.size(); i++){
            double distance = Arithmetic.euclideanDistance(centroid.get(i).getData(), sample.getData());//2
            if (distanceData.getType() == null || distanceData.getDistance() < distance){
                distanceData.setDistance(distance);
                distanceData.setType(String.valueOf(i));
            }
        }
        return "Cluster-"+distanceData.getType();
    }
}
