package AI.KNN;

public class DistanceData implements Comparable<DistanceData> { //Middle-man data container
    double distance;
    String type;

    public DistanceData(double distance, String type){
        this.distance = distance;
        this.type = type;
    }

    public DistanceData(){
        this.distance = Double.MAX_VALUE;
        this.type = null;
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

    public  void setDistance(double distance){
        this.distance = distance;
    }
    public  void setType(String type){
        this.type = type;
    }
}
