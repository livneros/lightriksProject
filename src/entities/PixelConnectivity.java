package entities;

import org.opencv.core.Mat;

import java.util.Map;

/**
 * Created by livne
 * on 25/10/2018.
 */
public abstract class PixelConnectivity {
    protected Mat image;

    public PixelConnectivity(Mat image) {
        this.image = image;
    }


    public Mat getImage() {
        return image;
    }

    public abstract Map<Coordinate, Double> getBoundaries(Coordinate coordinate);
}

