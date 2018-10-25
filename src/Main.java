
import entities.*;
import entities.implementations.CoordinateImpl;
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
    public static void main(String[] args) throws IOException, UnSupportedConnectivityType {
        String imagePath = args[0];
        double powerFactor = Double.valueOf(args[1]);
        double epsilon = Double.valueOf(args[2]);
        int connectivityType = Integer.valueOf(args[3]);

        ArrayList<ArrayList<Double>> imageArr = Utils.readImage(imagePath);
        for(int row = 1; row < 30; row++){
            for(int i = 1; i< 30; i++){
                imageArr.get(row).set(i, -2.0);
            }
        }
        Image image = new Image(imageArr);
        WeightFunction weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        PixelConnectivity pixelConnectivity = PixelConnectivityFactory.getPixelConnectivity(connectivityType, image);
        HoleImage holeImage = new HoleImage(image, weightFunction, pixelConnectivity, new CoordinateImpl());
        holeImage.findHoles();
        holeImage.findBoundaries();
        holeImage.fillHoles();

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
