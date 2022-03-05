package KNN;

import CSV.RowWithLabel;
import CSV.TableWithLabel;
import Utilities.Arithmetic;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class KNN implements KNNInterface{
    private final int k; //k nearest neighbors
    private final Map<List<Double>,String> dataTable;
    public KNN(){
        this.k = 5;
        this.dataTable = new HashMap<>();
    }
    public KNN(int k, HashMap<List<Double>, String> data){
        this.k = k;
        this.dataTable = data;
    }

    @Override
    public void train(TableWithLabel data) {
        for (RowWithLabel row: data.getDataTable()) {
            dataTable.put(row.getData(), row.getLabel());
        }
    }

    @Override
    public String estimate(List<Double> sample) {
        List<Double> column = (List<Double>) dataTable.keySet().toArray()[0];
        if (sample.size() != column.size()) throw new IllegalArgumentException("sample size must be equal size of the number of columns");
        double[] sampleArray =ArrayUtils.toPrimitive(sample.stream().toArray(Double[]::new));
        List<DistanceData> distances = addDistances(sampleArray);
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
        Collections.sort(sortedMap, Map.Entry.comparingByValue());
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

    private List<DistanceData> addDistances(double[] sample){
        List<DistanceData> distances = new ArrayList<>();
        for (Map.Entry<List<Double>, String> entry : dataTable.entrySet()){
            double[] distance = ArrayUtils.toPrimitive(entry.getKey().stream().toArray(Double[]::new));
            distances.add(new DistanceData(Arithmetic.euclideanDistance(distance, sample), entry.getValue()));
        }
        return distances;
    }

    @Override
    public Map<List<Double>,String> getDataTable(){
        return dataTable;
    }

}
