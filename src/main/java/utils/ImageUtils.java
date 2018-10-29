package utils;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.exceptions.MaskImageSizeException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

/**
 * Created by livne
 * on 29/10/2018.
 */
public class ImageUtils {

    private static final String OPENCV_LIB_VERSION = "opencv_java343";
    private static final String OPENCV_LIB_PREFIX = ".dll";
    private static final String FILES_FOLDER = "\\files\\";
    private static final String USER_DIRECTORY = "user.dir";
    private static final String TARGET_DIRECTORY = "target\\";
    private static final int B_W_IMAGE_CHANNELS_SIZE = 1;
    private static final int IMAGE_BACKGROUND_ADDITION = 50;
    public static final double HOLE = 0.0;
    public static final int RGB_CHANNELS_SIZE = 3;

    public static void save_image(Mat outputImage, String outputFileName) {
        Imgcodecs.imwrite(TARGET_DIRECTORY  + outputFileName, outputImage);
    }

    public static Mat getMaskedImage(String imagePath, String maskImagePath) throws IOException, MaskImageSizeException {
        Mat baseImage = getImage(imagePath);
        if(maskImagePath != null){
            convertToGrayScale(baseImage);
            Mat mask = getImage(maskImagePath);
            mask(mask);
            convertToGrayScale(mask);
            applyMask(baseImage, mask);
        }
        return baseImage;
    }

    private static void mask(Mat mask) {
        double[] insert = {HOLE,HOLE,HOLE};
        for(int i = 20; i< 30; i++){
            for (int j =20; j<40; j++){
                mask.put(i, j, insert);
            }
        }
    }

    private static void applyMask(Mat baseImage, Mat mask) throws MaskImageSizeException {
        validateMaskSize(baseImage, mask);
        for(int row = 0; row< mask.rows(); row ++){
            for (int col = 0; col< mask.cols(); col++){
                if(isHole(mask.get(row, col))){
                    baseImage.put(row, col, HOLE);
                }
            }
        }
    }

    static void validateMaskSize(Mat baseImage, Mat mask) throws MaskImageSizeException {
        if(baseImage.rows() < mask.rows() || baseImage.cols() < mask.cols()){
            throw new MaskImageSizeException();
        }
    }


    public static boolean isHole(double[] value) {
        return value[0] == HOLE;
    }


    private static Mat getImage(String imagePath) throws IOException {
        String openCvPath = System.getProperty(USER_DIRECTORY) + FILES_FOLDER;
        System.load(openCvPath + OPENCV_LIB_VERSION + OPENCV_LIB_PREFIX);
        File input = new File(imagePath);
        BufferedImage image = ImageIO.read(input);
        Mat sourceImage = getImageAccordingRgbOrGrayScale(image);
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        sourceImage.put(0,0, pixels);
        return sourceImage;
    }

    private static Mat getImageAccordingRgbOrGrayScale(BufferedImage image) {
        if(image.getRaster().getNumBands() == RGB_CHANNELS_SIZE) {
             return new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        }else{
             return new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
        }
    }

    private static void convertToGrayScale(Mat sourceImage) {
        Imgproc.cvtColor(sourceImage, sourceImage , Imgproc.COLOR_RGB2GRAY);
    }

    public static void displayImage(Mat image){
        BufferedImage bufferedImage = Mat2BufferedImage(image);
        displayImage(bufferedImage);

    }

    private static BufferedImage Mat2BufferedImage(Mat matImage){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( matImage.channels() > B_W_IMAGE_CHANNELS_SIZE) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matImage.channels()* matImage.cols()* matImage.rows();
        byte [] b = new byte[bufferSize];
        matImage.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(matImage.cols(), matImage.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }

    private static void displayImage(Image image)
    {
        ImageIcon icon=new ImageIcon(image);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(image.getWidth(null)+ IMAGE_BACKGROUND_ADDITION, image.getHeight(null)+ IMAGE_BACKGROUND_ADDITION);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
}
