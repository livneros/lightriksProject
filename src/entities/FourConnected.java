package entities;

import org.opencv.core.Mat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class FourConnected extends PixelConnectivity{

    private static final int STEP_DOWN_OR_LEFT = -1;
    private static final int STEP_UP_OR_RIGHT = 1;

    public FourConnected(Mat image) {
        super(image);
    }

    @Override
    public Map<Coordinate, Double> getBoundaries(Coordinate coordinate) {
        Map<Coordinate, Double> boundaries = new HashMap<>();
        List<Integer> possibleMovements = Arrays.asList(STEP_UP_OR_RIGHT, STEP_DOWN_OR_LEFT);
        int specRow;
        int specCol;
        for(int row: possibleMovements){
            for(int col: possibleMovements){
                specRow = coordinate.getRow() + row;
                specCol = coordinate.getCol() + col;
                if(!isHole(specRow, specCol)){
                    boundaries.put(new Coordinate(specRow, specCol), image.get(specRow, specCol)[0]);
                }
            }
        }
        return boundaries;
    }
}
