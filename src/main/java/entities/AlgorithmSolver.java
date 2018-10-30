package entities;

import org.opencv.core.Mat;
import utils.ImageUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.ImageUtils.isHole;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class AlgorithmSolver {
    private Mat image;
    private WeightFunction weightFunction;
    private PixelConnectivity pixelConnectivity;
    private Map<Coordinate, Boolean> holes;
    private Map<Coordinate, Double> boundaries;

    public AlgorithmSolver(Mat image, WeightFunction weightFunction, PixelConnectivity pixelConnectivity) {
        this.image = image;
        this.weightFunction = weightFunction;
        this.pixelConnectivity = pixelConnectivity;
        holes = new HashMap<>();
        boundaries = new HashMap<>();
    }

    public Mat getImage() {
        return image;
    }

    public Map<Coordinate, Boolean> getHoles() {
        return holes;
    }

    public Map<Coordinate, Double> getBoundaries() {
        return boundaries;
    }

    void findHoles() {
        for (int row = 0; row < image.rows(); row++) {
            for (int col = 0; col < image.cols(); col++) {
                if (isHole(image.get(row, col))) {
                    holes.put(new Coordinate(row, col), true);
                }
            }
        }
    }


    private void findBoundaries() {
        for (Coordinate coordinate : holes.keySet()) {
            List<Coordinate> neighbors = pixelConnectivity.getNeighbors(coordinate);
            neighbors.stream()
                    .filter(boundary -> !ImageUtils.isHole(image.get(boundary.getRow(), boundary.getCol())))
                    .forEach(boundary -> boundaries.put(boundary, getByCoordinate(boundary)));
        }
    }

    double getByCoordinate(Coordinate coordinate) {
        return image.get(coordinate.getRow(), coordinate.getCol())[0];
    }

    private void fillHoles() {
        for (Coordinate coordinate : getHoles().keySet()) {
            double fixedValue = weightFunction.solve(coordinate, boundaries);
            setPixel(coordinate, fixedValue);
        }
    }

    void setPixel(Coordinate coordinate, double fixedValue) {
        image.put(coordinate.getRow(), coordinate.getCol(), fixedValue);
    }

    public void fixHoles() {
        findHoles();
        findBoundaries();
        fillHoles();
    }


    public Mat fixHolesBonus() {
        int i, k = 0, l = 0, m = image.rows(), n = image.cols();
        while (k < m && l < n) {
            for (i = l; i < n; ++i) {
                assignApproximateValue(k, i);
            }
            k++;

            for (i = k; i < m; ++i) {
                assignApproximateValue(i, n - 1);
            }
            n--;

            if (k < m) {
                for (i = n - 1; i >= l; --i) {
                    assignApproximateValue(m - 1, i);
                }
                m--;
            }

            if (l < n) {
                for (i = m - 1; i >= k; --i) {
                    assignApproximateValue(i, l);
                }
                l++;
            }
        }
        return image;
    }

    private void assignApproximateValue(int i, int j) {
        if (isHole(image.get(i, j))) {
            Map<Coordinate, Double> coordinateToValue = new HashMap<>();
            Coordinate coordinate = new Coordinate(i, j);
            List<Coordinate> neighbors = pixelConnectivity.getNeighbors(coordinate);
            neighbors.stream().filter(neighbor -> !isHole(image.get(neighbor.getRow(), neighbor.getCol())))
                    .forEach(neighbor -> coordinateToValue.put(neighbor, getByCoordinate(neighbor)));
            double approximateValue = weightFunction.solve(coordinate, coordinateToValue);
            image.put(i, j, approximateValue);
        }
    }
}
