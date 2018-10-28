package entities;

import org.opencv.core.Mat;

import java.util.Map;

/**
 * Created by livne
 * on 25/10/2018.
 */
public abstract class PixelConnectivity {
    private static final double HOLE = 0.0;
    protected Mat image;

    public PixelConnectivity(Mat image) {
        this.image = image;
    }


    boolean isHole(int row, int col) {
        return image.get(row, col)[0] == HOLE;
    }

    public Mat getImage() {
        return image;
    }

    public abstract Map<Coordinate, Double> getBoundaries(Coordinate coordinate);
}

