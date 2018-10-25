package entities;

import java.util.ArrayList;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class EightConnected extends PixelConnectivity {

    public EightConnected(ArrayList<ArrayList<Integer>> image) {
        super(image);
    }

    @Override
    public boolean isBoundary(Coordinate coordinate) {
        for(int row = coordinate.getLeftUpperCornerRow(); row <= coordinate.getRightLowerCornerRow(); row++ ) {
            for (int col = coordinate.getLeftUpperCornerCol(); col <= coordinate.getRightLowerCornerCol(); col++) {
                if(coordinate.isItTheSameCoordinate(row, col)){
                    continue;
                }
                if(image.get(row).get(col) == HOLE){
                    return true;
                }
            }
        }
        return false;
    }

}
