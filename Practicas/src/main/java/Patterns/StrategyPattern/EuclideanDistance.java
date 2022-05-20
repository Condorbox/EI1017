package Patterns.StrategyPattern;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

public class EuclideanDistance implements IDistance {
    @Override
    public double calculateDistance(List<Double> p, List<Double> q) {
        if (p == null || q == null) throw new NullPointerException("data can't be nulls");
        double[] pArray = ArrayUtils.toPrimitive(p.toArray(Double[]::new));
        double[] qArray = ArrayUtils.toPrimitive(q.toArray(Double[]::new));
        return euclideanDistance(pArray, qArray);
    }

    private double euclideanDistance(double[] from, double[] to){
        if (from.length != to.length) throw new IllegalArgumentException("data must have the same size");
        double distance = 0;

        for (int i=0;i<from.length;i++){
            distance += Math.pow(from[i]-to[i],2);
        }

        return Math.sqrt(distance);
    }
}
