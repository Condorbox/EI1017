package CSV.AI.KNN;

import CSV.TableWithLabel;
import CSV.RowWithLabel;

import CSV.AI.DistanceClient;

import Patterns.FactoryPattern.DistanceType;
import Patterns.StrategyPattern.IDistance;

import java.util.*;

public class KNN implements KNNInterface, DistanceClient {
    private final int k; //k nearest neighbors
    private List<String> header;
    private final Map<List<Double>,String> dataTable;
    private IDistance distance;

    public KNN() {
        this.k = 5;
        this.dataTable = new HashMap<>();
        this.distance = DistanceType.EUCLIDEAN.getDistance();
        this.header = new ArrayList<>();
    }

    public KNN(int k, HashMap<List<Double>, String> data, List<String> label, DistanceType distanceType){
        this.k = k;
        this.dataTable = data;
        this.header = label;
        this.distance = distanceType.getDistance();
    }

    @Override
    public void train(TableWithLabel data) {
        dataTable.clear();
        header = data.getHeaders();
        for (RowWithLabel row: data.getDataTable()) {
            dataTable.put(row.getData(), row.getLabel());
        }
    }

    @Override
    public String estimate(List<Double> sample) {
        List<Double> column = (List<Double>) dataTable.keySet().toArray()[0];
        if (sample.size() != column.size()) throw new IllegalArgumentException("sample size must be equal size of the number of columns");
        List<DistanceData> distances = addDistances(sample);
        Collections.sort(distances);
        DistanceData[] kNearestNeighbours = selectKNearestNeighbors(distances);
        return mostCommon(kNearestNeighbours);
    }

    private String mostCommon(DistanceData[] neighbours){
        Map<String, Integer> repetitions = new HashMap<>();
        for (DistanceData neighbour: neighbours) {
            if (!repetitions.containsKey(neighbour.getType())){
                repetitions.put(neighbour.getType(), 1);
            }else{
                int numberOfRepetitions = repetitions.get(neighbour.getType());
                repetitions.put(neighbour.getType(), numberOfRepetitions + 1);
            }
        }
        List<Map.Entry<String, Integer>> sortedMap = new LinkedList<>(repetitions.entrySet());
        sortedMap.sort(Map.Entry.comparingByValue());
        return sortedMap.get(sortedMap.size()-1).getKey();
    }



    private DistanceData[] selectKNearestNeighbors(List<DistanceData> neighbours){
        if (k>neighbours.size()) throw new ArrayIndexOutOfBoundsException("K must be lower than the number of rows");
        DistanceData[] kNearestNeighbours = new DistanceData[k];
        for (int i = 0; i<k; i++){
            kNearestNeighbours[i] = neighbours.get(i);
        }
        return kNearestNeighbours;
    }

    private List<DistanceData> addDistances(List<Double> sample){
        List<DistanceData> distances = new ArrayList<>();
        for (Map.Entry<List<Double>, String> entry : dataTable.entrySet()){
            List<Double> data = entry.getKey();
            distances.add(new DistanceData(calculateDistance(data, sample), entry.getValue()));
        }
        return distances;
    }

    @Override
    public Map<List<Double>,String> getDataTable(){
        return dataTable;
    }

    @Override
    public void setDistance(IDistance distance) {
        this.distance = distance;
    }

    public List<String> getHeader(){
        return header;
    }

    public void setDistance(DistanceType distanceType){
        this.distance = distanceType.getDistance();
    }

    public double calculateDistance(List<Double> data, List<Double> sample){
        return distance.calculateDistance(data, sample);
    }
}
