package entities;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class Image {
    private static final Double HOLE = -2.0;
    public static final int FIRST_ELEMENT = 0;

    ArrayList<ArrayList<Double>> image;
    int height;
    int width;

    public Image(ArrayList<ArrayList<Double>> image) {
        this.image = image;
        this.height = image.size();
        this.width = image.get(FIRST_ELEMENT).size() / height;
    }

    public ArrayList<ArrayList<Double>> getImage() {
        return image;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isHole(int row, int col) {
        return row < height && col < width && Objects.equals(image.get(row).get(col), HOLE);
    }


    public void setPixel(Coordinate coordinate, double fixedValue) {
        image.get(coordinate.getRow()).set(coordinate.getCol(), fixedValue);
    }
}
