package entities;

import java.util.List;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class EightConnected extends PixelConnectivity {

    EightConnected() {
        super();
    }

    @Override
    public List<Coordinate> getNeighbors(Coordinate coordinate) {
        neighbors = super.getNeighbors(coordinate);
        for (int row = coordinate.getLeftUpperCornerRow(); row <= coordinate.getRightLowerCornerRow(); row++) {
            for (int col = coordinate.getLeftUpperCornerCol(); col <= coordinate.getRightLowerCornerCol(); col++) {
                if (coordinate.isItTheSameCoordinate(row, col)) {
                    continue;
                }
                neighbors.add(new Coordinate(row, col));
            }
        }
        return neighbors;
    }

}
