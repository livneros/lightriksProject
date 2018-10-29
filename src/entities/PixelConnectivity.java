package entities;

import java.util.List;

/**
 * Created by livne
 * on 25/10/2018.
 */
public interface PixelConnectivity {

    List<Coordinate> getNeighbors(Coordinate coordinate);

}

