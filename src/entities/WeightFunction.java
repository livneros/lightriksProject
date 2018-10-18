package entities;

import entities.implementations.CoordinateImpl;

public class WeightFunction {
    private static WeightFunction mInstance;
    private double powerFactor;
    private double epsilon;

    private WeightFunction(double powerFactor, double epsilon) {
        this.powerFactor = powerFactor;
        this.epsilon = epsilon;
    }

    static WeightFunction getInstance(double powerFactor, double epsilon) {
        if (mInstance == null) {
            mInstance = new WeightFunction(powerFactor, epsilon);
        }
        return mInstance;
    }

    double solve(Coordinate u, Coordinate v, CoordinateImpl coordinateImpl){
        double euclideanDistance = coordinateImpl.euclideanDistance(u, v);
        double denominator = Math.pow(euclideanDistance, this.powerFactor) + this.epsilon;
        return Math.pow(denominator, -1);
    }

    double getPowerFactor() {
        return powerFactor;
    }

    double getEpsilon() {
        return epsilon;
    }
}
