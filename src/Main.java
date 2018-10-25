
import entities.PixelConnectivity;
import entities.PixelConnectivityFactory;
import entities.WeightFunction;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String imagePath = args[0];
        double powerFactor = Double.valueOf(args[1]);
        double epsilon = Double.valueOf(args[2]);
        int connectivityType = Integer.valueOf(args[3]);

        ArrayList<ArrayList<ArrayList<Integer>>> image = Utils.readImage(imagePath);

        WeightFunction weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
//        PixelConnectivity pixelConnectivity = PixelConnectivityFactory.getPixelConnectivity(connectivityType, image);

//        File input = new File(imagePath);
//        BufferedImage image = ImageIO.read(input);
//        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//        Mat sourceImage = new Mat();
//        sourceImage.put(0,0, pixels);
//        Mat outputImage = new Mat();
//        Imgproc.cvtColor(sourceImage, outputImage , Imgproc.COLOR_RGB2GRAY);
//        System.out.println(outputImage.row(0).col(0));
    }
}
