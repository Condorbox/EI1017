package Patterns.FactoryPattern;

import Patterns.StrategyPattern.IDistance;

public interface IFactory {
    IDistance getDistance();
}
