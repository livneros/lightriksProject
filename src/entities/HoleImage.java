package entities;

import entities.implementations.CoordinateImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class HoleImage {
    private Image image;
    private WeightFunction weightFunction;
    private PixelConnectivity pixelConnectivity;
    CoordinateImpl coordinateImpl;
    Map<Coordinate, Boolean> holes;
    Map<Coordinate, Double> boundaries;

    public HoleImage(Image image, WeightFunction weightFunction, PixelConnectivity pixelConnectivity, CoordinateImpl coordinateImpl) {
        this.image = image;
        this.weightFunction = weightFunction;
        this.pixelConnectivity = pixelConnectivity;
        this.coordinateImpl = coordinateImpl;
        holes = new HashMap<>();
        boundaries = new HashMap<>();
    }

    public Image getImage() {
        return image;
    }

    public WeightFunction getWeightFunction() {
        return weightFunction;
    }

    public PixelConnectivity getPixelConnectivity() {
        return pixelConnectivity;
    }

    public Map<Coordinate, Boolean> getHoles() {
        return holes;
    }

    public Map<Coordinate, Double> getBoundaries() {
        return boundaries;
    }

    public void findHoles(){
        for(int row = 0; row < image.getHeight(); row++){
            for (int col = 0; col < image.getWidth(); col++){
                if(image.isHole(row, col)){
                    holes.put(new Coordinate(row, col), true);
                }
            }
        }
    }

    public void findBoundaries(){
        for(Coordinate coordinate: holes.keySet()){
            boundaries.putAll(pixelConnectivity.getBoundaries(coordinate));
        }
    }

    public void fillHoles(){
        for(Coordinate coordinate: holes.keySet()){
            double fixedValue = weightFunction.solve(coordinate, boundaries, coordinateImpl);
            image.setPixel(coordinate, fixedValue);
        }
    }
}
