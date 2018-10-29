package entities;

import org.opencv.core.Mat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.ImageUtils.isHole;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class HoleImage {
    private Mat image;
    private WeightFunction weightFunction;
    private PixelConnectivity pixelConnectivity;
    Map<Coordinate, Boolean> holes;
    Map<Coordinate, Double> boundaries;

    public HoleImage(Mat image, WeightFunction weightFunction, PixelConnectivity pixelConnectivity) {
        this.image = image;
        this.weightFunction = weightFunction;
        this.pixelConnectivity = pixelConnectivity;
        holes = new HashMap<>();
        boundaries = new HashMap<>();
    }

    public Mat getImage() {
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
        for(int row = 0; row < image.rows(); row++){
            for (int col = 0; col < image.cols(); col++){
                if(isHole(image, row, col)){
                    holes.put(new Coordinate(row, col), true);
                }
            }
        }
    }


    public void findBoundaries(){
        for(Coordinate coordinate: holes.keySet()){
            List<Coordinate> neighbors = pixelConnectivity.getNeighbors(coordinate);
            neighbors.stream()
                    .filter(boundary -> !isHole(image, boundary.getRow(), boundary.getCol()))
                    .forEach(boundary -> boundaries.put(boundary, getByCoordinate(boundary)));
        }
    }

    public double getByCoordinate(Coordinate coordinate){
        return image.get(coordinate.getRow(), coordinate.getCol())[0];
    }

    public void fillHoles(){
        for(Coordinate coordinate: holes.keySet()){
            double fixedValue = weightFunction.solve(coordinate, boundaries);
            setPixel(coordinate, fixedValue);
        }
    }

    private void setPixel(Coordinate coordinate, double fixedValue) {
        image.put(coordinate.getRow(), coordinate.getCol(), fixedValue);
    }

    public Mat fixHoles() {
        findHoles();
        findBoundaries();
        fillHoles();
        return image;
    }
}
