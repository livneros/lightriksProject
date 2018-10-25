package entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class EightConnected extends PixelConnectivity {

    public EightConnected(Image image) {
        super(image);
    }

    @Override
    public Map<Coordinate, Double> getBoundaries(Coordinate coordinate) {
        Map<Coordinate, Double> boundaries = new HashMap<>();
        for(int row = coordinate.getLeftUpperCornerRow(); row <= coordinate.getRightLowerCornerRow(); row++ ) {
            for (int col = coordinate.getLeftUpperCornerCol(); col <= coordinate.getRightLowerCornerCol(); col++) {
                if(coordinate.isItTheSameCoordinate(row, col)){
                    continue;
                }
                if(!image.isHole(row, col)){
                    boundaries.put(new Coordinate(row, col), image.getImage().get(row).get(col));
                }
            }
        }
        return boundaries;
    }

}
