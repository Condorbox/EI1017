package Utilities;

import CSV.Table;

public interface AlgorithmInterface<T extends Table<?>,W, E> {
    void train(T data);
    E estimate(W sample);
}
