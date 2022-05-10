package CSV.AI.LinearRegresion;

import CSV.Row;
import CSV.Table;
import Utilities.Arithmetic;
import org.apache.commons.lang3.ArrayUtils;

public class LinearRegression implements LinearRegressionInterface{
    private double slope;
    private double origin;
    private double error;

    public LinearRegression(){
        this.slope = 0;
        this.origin = 0;
        this.error = 0;
    }
    public LinearRegression(double slope, double origin, double error){
        this.slope = slope;
        this.origin = origin;
        this.error = error;
    }

    @Override
    public void train(Table<Row> data) {
        if (data.getHeaders().size() != 2) throw new IllegalArgumentException("Tabla must have just 2 columns");
        double[] xData = ArrayUtils.toPrimitive(data.getColumnAt(0).stream().toArray(Double[]::new));
        double[] yData = ArrayUtils.toPrimitive(data.getColumnAt(1).stream().toArray(Double[]::new));

        double standardDeviationX = Arithmetic.standardDeviation(xData);
        double standardDeviationY = Arithmetic.standardDeviation(yData);

        double correlationCoefficient = Arithmetic.Covariance(xData,yData)/(standardDeviationX*standardDeviationY);
        slope = correlationCoefficient*(standardDeviationY/standardDeviationX);
        origin = Arithmetic.mean(yData) - slope* Arithmetic.mean(xData);
        error = Math.sqrt((Arithmetic.variance(yData)*yData.length))/(yData.length - 2);
    }

    @Override
    public Double estimate(Double sample) {
        return origin + slope*sample;
    }

    @Override
    public Double[] estimateWithError(Double sample) {
        Double estimate = estimate(sample);
        return new Double[]{estimate - error, estimate + error};
    }

    @Override
    public Double getSlope() {
        return slope;
    }

    @Override
    public Double getOrigin() {
        return origin;
    }

    @Override
    public Double getError() {
        return error;
    }
}
