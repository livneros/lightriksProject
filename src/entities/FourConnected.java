package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class FourConnected implements PixelConnectivity{

    private static final int STEP_DOWN_OR_LEFT = -1;
    private static final int STEP_UP_OR_RIGHT = 1;

    public FourConnected() {
    }

    @Override
    public List<Coordinate> getNeighbors(Coordinate coordinate) {
        List<Coordinate> boundaries = new ArrayList<>();
        List<Integer> possibleMovements = Arrays.asList(STEP_UP_OR_RIGHT, STEP_DOWN_OR_LEFT);
        for(int step: possibleMovements){
            boundaries.add(new Coordinate(coordinate.getRow(), coordinate.getCol() + step));
            boundaries.add(new Coordinate(coordinate.getRow() + step, coordinate.getCol()));
        }
        return boundaries;
    }
}
