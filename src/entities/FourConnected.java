package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class FourConnected extends PixelConnectivity{

    private static final int STEP_DOWN_OR_LEFT = -1;
    private static final int STEP_UP_OR_RIGHT = 1;

    public FourConnected(ArrayList<ArrayList<Integer>> image) {
        super(image);
    }

    @Override
    public boolean isBoundary(Coordinate coordinate) {
        List<Integer> possibleMovements = Arrays.asList(STEP_UP_OR_RIGHT, STEP_DOWN_OR_LEFT);
        for(int row: possibleMovements){
            for(int col: possibleMovements){
                if(image.get(coordinate.getRow() + row).get(coordinate.getCol() + col) == HOLE){
                    return true;
                }
            }
        }
        return false;
    }
}
