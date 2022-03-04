package Utilities;

public final class Arithmetic {
    private Arithmetic(){}

    public static double mean(double[] data){
        if (data == null) throw new NullPointerException("Null data is forbidden");
        if (data.length <= 0) throw new ArithmeticException("Data must have at least one element");
        double sum = 0;
        for (Double number: data)
            sum += number;
        return sum / data.length;
    }

    public static double variance(double[] data){
        double sum = 0;
        double mean = mean(data);
        for (Double e: data)
            sum += Math.pow((e - mean), 2);
        return sum/data.length;
    }

    public static double standardDeviation(double[] data){
       return Math.sqrt(variance(data));
    }

    public static double Covariance(double[] xData, double[] yData){
        if (xData.length != yData.length) throw new IllegalArgumentException("Both data must be the same size");
        double sum = 0;
        double xMean = mean(xData);
        double yMean = mean(yData);
        for (int i = 0; i<xData.length;i++){
            sum += Math.abs(xData[i]-xMean)*Math.abs(yData[i]-yMean);
        }
        return sum / xData.length;
    }

    public static double euclideanDistance(double[] from, double[] to){
        if (from == null || to == null) throw new NullPointerException("data can't be nulls");
        if (from.length != to.length) throw new IllegalArgumentException("Vectors must have the same size");
        double distance = 0;

        for (int i=0;i<from.length;i++){
            distance += Math.pow(from[i]-to[i],2);
        }

        return Math.sqrt(distance);
    }
}
