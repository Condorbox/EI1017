package Patterns.FactoryPattern;

import Patterns.StrategyPattern.EuclideanDistance;
import Patterns.StrategyPattern.IDistance;
import Patterns.StrategyPattern.ManhattanDistance;

public enum DistanceType  {
    EUCLIDEAN(new EuclideanDistance()),
    MANHATTAN(new ManhattanDistance());

    private final IDistance distance;

    DistanceType(IDistance distance){
        this.distance = distance;
    }

    public IDistance getDistance(){
        return distance;
    }
}
