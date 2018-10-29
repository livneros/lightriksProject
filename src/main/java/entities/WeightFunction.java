package entities;

import java.util.Map;

public class WeightFunction {
    public static final double INITIAL_DOUBLE = 0.0;
    public static final double INITIAL_IN_CASE_OF_EMPTY_BOUNDARIES = 1.0;
    private static WeightFunction mInstance;
    private double powerFactor;
    private double epsilon;

    private WeightFunction(double powerFactor, double epsilon) {
        this.powerFactor = powerFactor;
        this.epsilon = epsilon;
    }

    public static WeightFunction getInstance(double powerFactor, double epsilon) {
        if (mInstance == null) {
            mInstance = new WeightFunction(powerFactor, epsilon);
        }
        return mInstance;
    }

    double solvePerCoordinate(Coordinate u, Coordinate v){
        double euclideanDistance = u.euclideanDistance(v);
        double denominator = Math.pow(euclideanDistance, getPowerFactor()) + getEpsilon();
        return Math.pow(denominator, -1);
    }

    double solve(Coordinate u, Map<Coordinate, Double> boundaries){
        double numerator  = INITIAL_DOUBLE;
        double denominator = boundaries.isEmpty() ? INITIAL_IN_CASE_OF_EMPTY_BOUNDARIES : INITIAL_DOUBLE;
        double euclideanDis;
        for(Coordinate boundary: boundaries.keySet()){
            euclideanDis = solvePerCoordinate(u, boundary);
            numerator  += (euclideanDis * boundaries.get(boundary));
            denominator += euclideanDis;
        }
        return numerator  / denominator;
    }

    double getPowerFactor() {
        return powerFactor;
    }

    double getEpsilon() {
        return epsilon;
    }
}
