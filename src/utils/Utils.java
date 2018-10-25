package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    private static final String MUST_NOT_BE_NULL_MSG = "must not be null.";

    public static void CheckNotNull(Object object, String objectName) throws Exception {
        if(object == null){
            throw new Exception(objectName + " " + MUST_NOT_BE_NULL_MSG);
        }
    }

    /**
     * assumptions: 1. the function handles a valid Url, relative or full.
     *              2. pixels should be Integer in the range of 0-255.
     * @param path - to a valid image.
     * @return array of arrays, that holds an array represents a pixel. row -> col -> pixel.
     * @throws IOException - on a broken path.
     */
    public static ArrayList<ArrayList<Double>> readImage(String path) throws IOException {
        File imgPath = new File(path);
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        return convertTo2DWithoutUsingGetRGB(bufferedImage);
    }
    private static ArrayList<ArrayList<Double>> convertTo2DWithoutUsingGetRGB(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        ArrayList<ArrayList<Double>> rows = new ArrayList<>();
        ArrayList<Double> cols = new ArrayList<>();
        int pixelLength = image.getRaster().getNumBands();
        for (int pixelIndex = 0, col = 0; pixelIndex < pixels.length; pixelIndex += pixelLength) {
            Double argb = buildPixel(pixels, pixelLength, pixelIndex);
            cols.add(argb);
            col++;
            if (isEndOfRow(width, col)) {
                col = 0;
                rows.add(cols);
            }
        }
        return rows;
    }

    private static boolean isEndOfRow(int width, int col) {
        return col == width;
    }

    /**
     * some images have alpha, red,green and blue per pixels, some only rgb and the black& white images have only one
     * entry per pixelIndex. this function should handle those differences.
     * @param pixels - a 1D list of all pixels in the image.
     * @param pixelLength - the size of entries in a pixelIndex.
     * @param pixelIndex - index for the pixels beginning in the pixels list.
     * @return an array represents a pixel.
     */
    private static Double buildPixel(byte[] pixels, int pixelLength, int pixelIndex) {
        return ((double)pixels[pixelIndex]);
//        ArrayList<Double> builtPixel = new ArrayList<>();
//        if(pixelLength == 1){
//            builtPixel.add((int) pixels[pixelIndex]);
//            return builtPixel;
//        }
//        builtPixel.add((int) pixels[getRedPosition(pixelLength, pixelIndex)]); // red
//        builtPixel.add((int) pixels[getGreenPosition(pixelLength, pixelIndex)]); // green
//        builtPixel.add((int) pixels[getBluePosition(pixelLength, pixelIndex)]); // blue
//        return builtPixel;
    }

    private static int getRedPosition(int pixelLength, int pixel) {
        return pixel + pixelLength - 1;
    }

    private static int getGreenPosition(int pixelLength, int pixel) {
        return pixel + pixelLength - 2;
    }

    private static int getBluePosition(int pixelLength, int pixel) {
        return pixel + pixelLength - 3;
    }
}
