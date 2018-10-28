import entities.*;
import entities.implementations.CoordinateImpl;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class Main {

    public static final String OPENCV_LIB_VERSION = "opencv_java343";
    public static final String OPENCV_LIB_PREFIX = ".dll";
    public static final String FILES_FOLDER = "\\files\\";
    public static final String USER_DIRECTORY = "user.dir";
    public static final String TARGET_DIRECTORY = "target\\";
    public static final String OUTPUT_FILE_NAME = "output.jpg";

    public static void main(String[] args) throws IOException, UnSupportedConnectivityType {
        String imagePath = args[0];
//        nu.pattern.OpenCV.;
        double powerFactor = Double.valueOf(args[1]);
        double epsilon = Double.valueOf(args[2]);
        int connectivityType = Integer.valueOf(args[3]);
        Mat outputImage = getImage(imagePath);
        System.out.println(outputImage.rows());
        System.out.println(outputImage.cols());
        for(int i = 150; i< 170; i++){
            for(int j = 90; j< 110; j++){
                outputImage.put(i, j, 0.0);
            }
        }
        save_image(outputImage, "temp.jpg");
        WeightFunction weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        PixelConnectivity pixelConnectivity = PixelConnectivityFactory.getPixelConnectivity(connectivityType, outputImage);
        HoleImage holeImage = new HoleImage(outputImage, weightFunction, pixelConnectivity, new CoordinateImpl());
        outputImage = holeImage.fixHoles();
        save_image(outputImage, OUTPUT_FILE_NAME);
    }

    private static void save_image(Mat outputImage, String outputFileName) {
        Imgcodecs.imwrite(TARGET_DIRECTORY  + outputFileName, outputImage);
    }

    private static Mat getImage(String imagePath) throws IOException {
        String opencvpath = System.getProperty(USER_DIRECTORY) + FILES_FOLDER;
        System.load(opencvpath + OPENCV_LIB_VERSION + OPENCV_LIB_PREFIX);
        File input = new File(imagePath);
        BufferedImage image = ImageIO.read(input);
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat sourceImage = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        sourceImage.put(0,0, pixels);
        Mat outputImage = new Mat();
        Imgproc.cvtColor(sourceImage, outputImage , Imgproc.COLOR_RGB2GRAY);

        return outputImage;
    }

    public static BufferedImage Mat2BufferedImage(Mat m){
// source: http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
// Fastest code
// The output can be assigned either to a BufferedImage or to an Image

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }

    public static void displayImage(Image img2)
    {
        //BufferedImage img=ImageIO.read(new File("/HelloOpenCV/lena.png"));
        ImageIcon icon=new ImageIcon(img2);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(img2.getWidth(null)+50, img2.getHeight(null)+50);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
