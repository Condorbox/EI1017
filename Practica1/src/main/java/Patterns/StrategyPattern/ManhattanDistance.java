package Patterns.StrategyPattern;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

public class ManhattanDistance implements Distance{
    @Override
    public double calculateDistance(List<Double> p, List<Double> q) {
        if (p == null || q == null) throw new NullPointerException("data can't be nulls");
        double[] pArray = ArrayUtils.toPrimitive(p.toArray(Double[]::new));
        double[] qArray = ArrayUtils.toPrimitive(q.toArray(Double[]::new));
        return manhattanDistance(pArray, qArray);
    }

    private double manhattanDistance(double[] from, double[] to){
        if (from.length != to.length) throw new IllegalArgumentException("data must have the same size");
        double distance = 0;

        for (int i=0;i<from.length;i++){
            distance += Math.abs(from[i]-to[i]);
        }

        return distance;
    }
}
