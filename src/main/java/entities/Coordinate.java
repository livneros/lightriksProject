package entities;

public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Coordinate() {
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    int getLeftUpperCornerRow() {
        return this.row - 1;
    }

    int getRightLowerCornerRow() {
        return this.row + 1;
    }

    int getRightLowerCornerCol() {
        return this.col + 1;
    }

    int getLeftUpperCornerCol() {
        return this.col - 1;
    }

    boolean isItTheSameCoordinate(int row, int col) {
        return row == getRow() && col == getCol();
    }

    double euclideanDistance(Coordinate v) {
        double sumOfDifferences = Math.pow(this.getRow() - v.getRow(), 2) + Math.pow(this.getCol() - v.getCol(), 2);
        return Math.sqrt(sumOfDifferences);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}
