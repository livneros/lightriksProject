package entities;

import java.util.ArrayList;

/**
 * Created by livne
 * on 25/10/2018.
 */
public abstract class PixelConnectivity {
    protected static final int HOLE = -1;
    protected ArrayList<ArrayList<Integer>> image;

    public PixelConnectivity(ArrayList<ArrayList<Integer>> image) {
        this.image = image;
    }

    public ArrayList<ArrayList<Integer>> getImage() {
        return image;
    }

    public abstract boolean isBoundary(Coordinate coordinate);
}

