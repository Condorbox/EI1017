package Patterns.FactoryPattern;

import Patterns.StrategyPattern.EuclideanDistance;
import Patterns.StrategyPattern.IDistance;
import Patterns.StrategyPattern.ManhattanDistance;

public enum DistanceType implements IFactory {
    EUCLIDEAN(new EuclideanDistance()),
    MANHATTAN(new ManhattanDistance());

    private final IDistance distance;

    DistanceType(IDistance distance){
        this.distance = distance;
    }

    @Override
    public IDistance getDistance(){
        return distance;
    }
}
