package LinearRegresion;

import CSV.Row;
import CSV.Table;
import org.apache.commons.lang3.ArrayUtils;

public class LinearRegression implements LinearRegressionInterface<Table<Row>, Double> {
    private double slope;
    private double origin;

    public LinearRegression(){
        this.slope = 0;
        this.origin = 0;
    }
    public LinearRegression(double slope, double origin){
        this.slope = slope;
        this.origin = origin;
    }

    public void train(Table<Row> data) {
        if (data.getHeaders().size() != 2) throw new IllegalArgumentException("Tabla must have just 2 columns");
        double[] xData = ArrayUtils.toPrimitive(data.getColumnAt(0).stream().toArray(Double[]::new));
        double[] yData = ArrayUtils.toPrimitive(data.getColumnAt(1).stream().toArray(Double[]::new));

        double standardDeviationX = Arithmetic.standardDeviation(xData);
        double standardDeviationY = Arithmetic.standardDeviation(yData);

        double correlationCoefficient = Arithmetic.Covariance(xData,yData)/(standardDeviationX*standardDeviationY);
        slope = correlationCoefficient*(standardDeviationY/standardDeviationX);
        origin = Arithmetic.mean(yData) - slope*Arithmetic.mean(xData);
    }

    public Double estimate(Double sample) {
        Double estimate = origin + slope*sample.doubleValue();
        return estimate;
    }

    public Double getSlope() {
        return slope;
    }

    public Double getOrigin() {
        return origin;
    }
}
