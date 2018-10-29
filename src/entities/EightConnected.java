package entities;

import org.opencv.core.Mat;

import java.util.HashMap;
import java.util.Map;

import static utils.ImageUtils.isHole;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class EightConnected extends PixelConnectivity {

    public EightConnected(Mat image) {
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
                if(!isHole(image, row, col)){
                    boundaries.put(new Coordinate(row, col), image.get(row, col)[0]);
                }
            }
        }
        return boundaries;
    }

}
