package utils;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.opencv.core.Mat;
import utils.exceptions.MaskImageSizeException;

/**
 * Created by Livne
 * on 29/10/2018.
 */
public class ImageUtilsTest {

    @Test (expected = MaskImageSizeException.class)
    public void validateMaskSize_MaskColsTooBig() throws Exception {
        Mat base = Mockito.mock(Mat.class);
        Mat mask = Mockito.mock(Mat.class);
        Mockito.when(base.rows()).thenReturn(0);
        Mockito.when(base.cols()).thenReturn(0);

        Mockito.when(mask.cols()).thenReturn(1);
        Mockito.when(mask.rows()).thenReturn(0);
        ImageUtils.validateMaskSize(base, mask);
    }
    @Test (expected = MaskImageSizeException.class)
    public void validateMaskSize_MaskRowsTooBig() throws Exception {
        Mat base = Mockito.mock(Mat.class);
        Mat mask = Mockito.mock(Mat.class);
        Mockito.when(base.rows()).thenReturn(0);
        Mockito.when(base.cols()).thenReturn(0);

        Mockito.when(mask.cols()).thenReturn(0);
        Mockito.when(mask.rows()).thenReturn(1);
        ImageUtils.validateMaskSize(base, mask);
    }
    @Test (expected = MaskImageSizeException.class)
    public void validateMaskSize_MaskTooBig() throws Exception {
        Mat base = Mockito.mock(Mat.class);
        Mat mask = Mockito.mock(Mat.class);
        Mockito.when(base.rows()).thenReturn(0);
        Mockito.when(base.cols()).thenReturn(0);

        Mockito.when(mask.cols()).thenReturn(1);
        Mockito.when(mask.rows()).thenReturn(1);
        ImageUtils.validateMaskSize(base, mask);
    }
    @Test
    public void validateMaskSize_sameSize() throws Exception {
        Mat base = Mockito.mock(Mat.class);
        Mat mask = Mockito.mock(Mat.class);
        int rowsAndColsSize = 1;
        Mockito.when(base.rows()).thenReturn(rowsAndColsSize);
        Mockito.when(base.cols()).thenReturn(rowsAndColsSize);

        Mockito.when(mask.cols()).thenReturn(rowsAndColsSize);
        Mockito.when(mask.rows()).thenReturn(rowsAndColsSize);
        ImageUtils.validateMaskSize(base, mask);
    }
    @Test
    public void validateMaskSize_baseBiggerThanMask() throws Exception {
        Mat base = Mockito.mock(Mat.class);
        Mat mask = Mockito.mock(Mat.class);
        int baseRowsAndColsSize = 1;
        int maskRowsAndColsSize = 0;
        Mockito.when(base.rows()).thenReturn(baseRowsAndColsSize);
        Mockito.when(base.cols()).thenReturn(baseRowsAndColsSize);

        Mockito.when(mask.cols()).thenReturn(maskRowsAndColsSize);
        Mockito.when(mask.rows()).thenReturn(maskRowsAndColsSize);
        ImageUtils.validateMaskSize(base, mask);
    }

    @Test
    public void isHole_notHole() {
        Mat image = Mockito.mock(Mat.class);
        double[] notHole = {300.0};
        int row = 0;
        int col = 0;
        Mockito.when(image.get(row, col)).thenReturn(notHole);
        Assert.assertFalse(ImageUtils.isHole(image.get(row, col)));
    }
    @Test
    public void isHole_Hole() {
        Mat image = Mockito.mock(Mat.class);
        int row = 0;
        int col = 0;
        double[] res = {ImageUtils.HOLE};
        Mockito.when(image.get(row, col)).thenReturn(res);
        Assert.assertTrue(ImageUtils.isHole(image.get(row, col)));
    }

}