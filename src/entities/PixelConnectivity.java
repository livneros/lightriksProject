package entities;

import java.util.Map;

/**
 * Created by livne
 * on 25/10/2018.
 */
public abstract class PixelConnectivity {
    protected Image image;

    public PixelConnectivity(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public abstract Map<Coordinate, Double> getBoundaries(Coordinate coordinate);
}

