package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by livne
 * on 25/10/2018.
 */
public abstract class PixelConnectivity {

    protected List<Coordinate> neighbors;

    protected PixelConnectivity(){
        neighbors = new ArrayList<>();
    }



    public List<Coordinate> getNeighbors(Coordinate coordinate){
        return new ArrayList<>();
    }

}

