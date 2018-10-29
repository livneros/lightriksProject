package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class EightConnected implements PixelConnectivity {

    public EightConnected() {
    }

    @Override
    public List<Coordinate> getNeighbors(Coordinate coordinate) {
        List<Coordinate> boundaries = new ArrayList<>();
        for(int row = coordinate.getLeftUpperCornerRow(); row <= coordinate.getRightLowerCornerRow(); row++ ) {
            for (int col = coordinate.getLeftUpperCornerCol(); col <= coordinate.getRightLowerCornerCol(); col++) {
                if(coordinate.isItTheSameCoordinate(row, col)){
                    continue;
                }
                boundaries.add(new Coordinate(row, col));
            }
        }
        return boundaries;
    }

}
