package LinearRegresion;

public interface LinearRegressionInterface<T,W> {
    void train(T data);
    W[] estimate(W sample);
    W getSlope();
    W getOrigin();
    W getError();
}
