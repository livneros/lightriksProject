package entities;

import org.junit.Assert;
import org.junit.Test;
import utils.Constants;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoordinateTest {


    public CoordinateTest() {
    }

    @Test
    public void euclideanDistance_sameCoordintes() {
        // u = (1, 2), v = (1, 2)
        Coordinate v = mock(Coordinate.class);
        Coordinate u = mock(Coordinate.class);
        int row = 1;
        when(u.getRow()).thenReturn(row);
        when(v.getRow()).thenReturn(row);
        int col = 2;
        when(u.getCol()).thenReturn(col);
        when(v.getCol()).thenReturn(col);
        when(u.euclideanDistance(v)).thenCallRealMethod();
        double distance = u.euclideanDistance(v);
        Assert.assertEquals(0, distance, Constants.DEFAULT_DELTA);
    }

    @Test
    public void euclideanDistance_zeroCoordintes() {
        // u = (0, 0), v = (0, 0)
        Coordinate v = mock(Coordinate.class);
        Coordinate u = mock(Coordinate.class);
        int row = 0;
        when(u.getRow()).thenReturn(row);
        when(v.getRow()).thenReturn(row);
        int col = 0;
        when(u.getCol()).thenReturn(col);
        when(v.getCol()).thenReturn(col);
        when(u.euclideanDistance(v)).thenCallRealMethod();
        double distance = u.euclideanDistance(v);
        Assert.assertEquals(0, distance, Constants.DEFAULT_DELTA);
    }

    @Test
    public void euclideanDistance_negativeCoordintes() {
        // u = (-5, 2), v = (-1, -1)
        Coordinate v = mock(Coordinate.class);
        Coordinate u = mock(Coordinate.class);
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
        Assert.assertEquals(5, distance, Constants.DEFAULT_DELTA);
    }

    @Test
    public void isItTheSameCoordinate_yes() throws Exception {
        Coordinate u = mock(Coordinate.class);
        int rowAndCol = 1;
        when(u.getCol()).thenReturn(rowAndCol);
        when(u.getRow()).thenReturn(rowAndCol);
        when(u.isItTheSameCoordinate(rowAndCol, rowAndCol)).thenCallRealMethod();
        Assert.assertTrue(u.isItTheSameCoordinate(rowAndCol, rowAndCol));
    }
    @Test
    public void isItTheSameCoordinate_no() throws Exception {
        Coordinate u = mock(Coordinate.class);
        int rowAndColU = 1;
        int rowAndColV = 2;
        when(u.getCol()).thenReturn(rowAndColU);
        when(u.getRow()).thenReturn(rowAndColU);
        when(u.isItTheSameCoordinate(rowAndColU, rowAndColU)).thenCallRealMethod();
        Assert.assertFalse(u.isItTheSameCoordinate(rowAndColV, rowAndColV));
    }
}
