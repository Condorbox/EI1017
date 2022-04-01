package Patterns.StrategyPattern;

import java.util.List;

public interface IDistance {
    double calculateDistance(List<Double> p, List<Double> q);
}
