package KNN;

public class DistanceData implements Comparable<DistanceData> { //Middle-man data container
    double distance;
    String type;

    public DistanceData(double distance, String type){
        this.distance = distance;
        this.type = type;
    }

    @Override
    public int compareTo(DistanceData otherDistance) {
        return Double.compare(distance, otherDistance.getDistance());
    }

    public double getDistance() {
        return distance;
    }
    public String getType(){
        return type;
    }
}
