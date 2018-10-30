package entities;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Livne
 * on 25/10/2018.
 */
public class FourConnected extends PixelConnectivity{

    private static final int STEP_DOWN_OR_LEFT = -1;
    private static final int STEP_UP_OR_RIGHT = 1;

    FourConnected() {
        super();
    }

    @Override
    public List<Coordinate> getNeighbors(Coordinate coordinate) {
        neighbors = super.getNeighbors(coordinate);
        List<Integer> possibleMovements = Arrays.asList(STEP_UP_OR_RIGHT, STEP_DOWN_OR_LEFT);
        for(int step: possibleMovements){
            neighbors.add(new Coordinate(coordinate.getRow(), coordinate.getCol() + step));
            neighbors.add(new Coordinate(coordinate.getRow() + step, coordinate.getCol()));
        }
        return neighbors;
    }
}
