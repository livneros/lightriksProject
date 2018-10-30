package entities;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Livne
 * on 29/10/2018.
 */
public class EightConnectedTest {

    private static final int EIGHT_CONNECTED_SIZE = 8;

    @Test
    public void getNeighbors() {
        Coordinate u = new Coordinate(1, 1);
        EightConnected eight = new EightConnected();
        List<Coordinate> neighbors = eight.getNeighbors(u);
        Assert.assertEquals(EIGHT_CONNECTED_SIZE, neighbors.size());
        Assert.assertTrue(neighbors.contains(new Coordinate(0,0)));
        Assert.assertTrue(neighbors.contains(new Coordinate(0,1)));
        Assert.assertTrue(neighbors.contains(new Coordinate(0,2)));
        Assert.assertTrue(neighbors.contains(new Coordinate(1,2)));
        Assert.assertTrue(neighbors.contains(new Coordinate(1,0)));
        Assert.assertTrue(neighbors.contains(new Coordinate(2,0)));
        Assert.assertTrue(neighbors.contains(new Coordinate(2,1)));
        Assert.assertTrue(neighbors.contains(new Coordinate(2,2)));
    }
    @Test
    public void getNeighbors_edgePixelCase() {
        Coordinate u = new Coordinate(0, 0);
        EightConnected eight = new EightConnected();
        List<Coordinate> neighbors = eight.getNeighbors(u);
        Assert.assertEquals(EIGHT_CONNECTED_SIZE, neighbors.size());
        Assert.assertTrue(neighbors.contains(new Coordinate(-1,-1)));
        Assert.assertTrue(neighbors.contains(new Coordinate(-1,0)));
        Assert.assertTrue(neighbors.contains(new Coordinate(-1,-1)));
        Assert.assertTrue(neighbors.contains(new Coordinate(1,1)));
        Assert.assertTrue(neighbors.contains(new Coordinate(1,0)));
        Assert.assertTrue(neighbors.contains(new Coordinate(1,-1)));
        Assert.assertTrue(neighbors.contains(new Coordinate(0,1)));
        Assert.assertTrue(neighbors.contains(new Coordinate(0,-1)));
    }
}