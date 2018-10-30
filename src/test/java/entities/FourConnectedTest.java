package entities;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Livne
 * on 29/10/2018.
 */
public class FourConnectedTest {

    private static final int FOUR_CONNECTED_SIZE = 4;

    @Test
    public void getNeighbors() {
        Coordinate u = new Coordinate(1, 1);
        FourConnected four = new FourConnected();
        List<Coordinate> neighbors = four.getNeighbors(u);
        Assert.assertEquals(FOUR_CONNECTED_SIZE, neighbors.size());
        Assert.assertTrue(neighbors.contains(new Coordinate(0,1)));
        Assert.assertTrue(neighbors.contains(new Coordinate(1,2)));
        Assert.assertTrue(neighbors.contains(new Coordinate(1,0)));
        Assert.assertTrue(neighbors.contains(new Coordinate(2,1)));
    }
    @Test
    public void getNeighbors_edgePixelCase() {
        Coordinate u = new Coordinate(0, 0);
        FourConnected four = new FourConnected();
        List<Coordinate> neighbors = four.getNeighbors(u);
        Assert.assertEquals(FOUR_CONNECTED_SIZE, neighbors.size());
        Assert.assertTrue(neighbors.contains(new Coordinate(0,1)));
        Assert.assertTrue(neighbors.contains(new Coordinate(-1,0)));
        Assert.assertTrue(neighbors.contains(new Coordinate(1,0)));
        Assert.assertTrue(neighbors.contains(new Coordinate(0,-1)));
    }
}