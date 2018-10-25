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

    public int getLeftUpperCornerRow(){
        return this.row - 1;
    }
    public int getRightLowerCornerRow(){
        return this.row + 1;
    }
    public int getRightLowerCornerCol(){
        return this.row + 1;
    }
    public int getLeftUpperCornerCol(){
        return this.col - 1;
    }
    boolean isItTheSameCoordinate(int row, int col) {
        return row == this.row && col == this.col;
    }

}
