package entities.implementations;

import entities.Coordinate;

public class CoordinateImpl {

    public CoordinateImpl() {
    }

    public double euclideanDistance(Coordinate u, Coordinate v){
        double sumOfDifferences = Math.pow(u.getRow() - v.getRow(), 2) + Math.pow(u.getCol() - v.getCol(), 2);
        return Math.sqrt(sumOfDifferences);
    }

}
