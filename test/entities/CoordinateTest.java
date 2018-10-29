package entities;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static utils.Constants.DEFAULT_DELTA;

public class CoordinateTest {


    public CoordinateTest() {
    }

    @Test
    public void euclideanDistance_sameCoordintes() {
        // u = (1, 2), v = (1, 2)
        Coordinate v = Mockito.mock(Coordinate.class);
        Coordinate u = Mockito.mock(Coordinate.class);
        int row = 1;
        when(u.getRow()).thenReturn(row);
        when(v.getRow()).thenReturn(row);
        int col = 2;
        when(u.getCol()).thenReturn(col);
        when(v.getCol()).thenReturn(col);
        when(u.euclideanDistance(v)).thenCallRealMethod();
        double distance = u.euclideanDistance(v);
        Assert.assertEquals(0, distance, DEFAULT_DELTA);
    }

    @Test
    public void euclideanDistance_zeroCoordintes() {
        // u = (0, 0), v = (0, 0)
        Coordinate v = Mockito.mock(Coordinate.class);
        Coordinate u = Mockito.mock(Coordinate.class);
        int row = 0;
        when(u.getRow()).thenReturn(row);
        when(v.getRow()).thenReturn(row);
        int col = 0;
        when(u.getCol()).thenReturn(col);
        when(v.getCol()).thenReturn(col);
        when(u.euclideanDistance(v)).thenCallRealMethod();
        double distance = u.euclideanDistance(v);
        Assert.assertEquals(0, distance, DEFAULT_DELTA);
    }

    @Test
    public void euclideanDistance_negativeCoordintes() {
        // u = (-5, 2), v = (-1, -1)
        Coordinate v = Mockito.mock(Coordinate.class);
        Coordinate u = Mockito.mock(Coordinate.class);
        int uRow = -5;
        int uCol = 2;
        int vRow = -1;
        int vCol = -1;
        when(u.getRow()).thenReturn(uRow);
        when(v.getRow()).thenReturn(vRow);
        when(u.getCol()).thenReturn(uCol);
        when(v.getCol()).thenReturn(vCol);
        when(u.euclideanDistance(v)).thenCallRealMethod();
        double distance = u.euclideanDistance(v);
        Assert.assertEquals(5, distance, DEFAULT_DELTA);
    }
}
