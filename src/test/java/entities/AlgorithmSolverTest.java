package entities;

import org.junit.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import utils.Constants;
import utils.ImageUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Created by livne
 * on 29/10/2018.
 */
public class AlgorithmSolverTest {

    @Test
    public void findHoles_noHoles(){
        Mat mat = mock(Mat.class);
        double[] notHole = {3.0};
        when(mat.get(0,0)).thenReturn(notHole);
        when(mat.get(0,1)).thenReturn(notHole);
        when(mat.get(1,0)).thenReturn(notHole);
        when(mat.get(1,1)).thenReturn(notHole);
        int rowAndColsSize = 2;
        when(mat.rows()).thenReturn(rowAndColsSize);
        when(mat.cols()).thenReturn(rowAndColsSize);
        AlgorithmSolver algorithmSolver = new AlgorithmSolver(mat, null, null);
        algorithmSolver.findHoles();
        assertEquals(0, algorithmSolver.getHoles().size());
    }
    @Test
    public void findHoles_oneHole(){
        Mat mat = mock(Mat.class);
        double[] notHole = {3.0};
        double[] hole = {ImageUtils.HOLE};
        when(mat.get(0,0)).thenReturn(hole);
        when(mat.get(0,1)).thenReturn(notHole);
        when(mat.get(1,0)).thenReturn(notHole);
        when(mat.get(1,1)).thenReturn(notHole);
        int rowAndColsSize = 2;
        when(mat.rows()).thenReturn(rowAndColsSize);
        when(mat.cols()).thenReturn(rowAndColsSize);
        AlgorithmSolver algorithmSolver = new AlgorithmSolver(mat, null, null);
        algorithmSolver.findHoles();
        assertEquals(1, algorithmSolver.getHoles().size());
        assertNotNull(algorithmSolver.getHoles().get(new Coordinate(0,0)));
    }


    @Test
    public void fillHoles_GetByCoordinate_with_A_Hole() {
        ImageUtils.loadOpenCv();
        Mat mat = new Mat(1, 1, CvType.CV_8UC1);
        AlgorithmSolver algorithmSolver = new AlgorithmSolver(mat, null, null);
        double fixedValue = 2.0;
        Coordinate coordinate = new Coordinate(0, 0);
        algorithmSolver.setPixel(coordinate, fixedValue);
        assertEquals(fixedValue, algorithmSolver.getByCoordinate(coordinate), Constants.DEFAULT_DELTA);
    }

}