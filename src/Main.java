import entities.*;
import org.opencv.core.Mat;
import utils.ImageUtils;
import utils.Utils;
import utils.exceptions.MaskImageSizeException;

import javax.ws.rs.BadRequestException;
import java.io.IOException;

import static utils.Constants.MINIMAL_EPSILON_ALLOWED;
import static utils.ImageUtils.save_image;

public class Main {


    private static final int POWER_FACTOR_INDEX = 0;
    private static final int EPSILON_INDEX = 1;
    private static final int CONNECTIVITY_TYPE_INDEX = 2;
    private static final int IMAGE_PATH_INDEX = 3;
    private static final int MASK_IMAGE_PATH_INDEX = 4;
    private static final int MINIMAL_INPUT_SIZE = 4;
    private static final int INPUT_SIZE_WITH_A_MASK_IMAGE= 5;
    private static final String INPUT_IS_MISSING_ARGUMENTS = "Input is missing arguments.";
    private static final String MUST_BE_A_VALID_NUMBER = " must be a valid number";
    private static final String MUST_BE_POSITIVE = " must be a positive number";
    private static final String POWER_FACTOR = "Power factor";
    private static final String EPSILON = "Epsilon";
    private static final String OUTPUT_FILE_NAME = "output.jpg";
    private static final int LOWEST_NON_NEGATIVE = 0;

    public static void main(String[] args) throws IOException, UnSupportedConnectivityType, MaskImageSizeException {
        validateInput(args);
        double powerFactor = getPowerFactor(args[POWER_FACTOR_INDEX]);
        double epsilon = getEpsilon(args[EPSILON_INDEX]);
        int connectivityType = getConnectivityType(args[CONNECTIVITY_TYPE_INDEX]);
        String imagePath = args[IMAGE_PATH_INDEX];
        String maskImagePath = null;
        if(isMaskImageProvided(args)){
            maskImagePath = args[MASK_IMAGE_PATH_INDEX];
        }
        Mat outputImage = ImageUtils.getMaskedImage(imagePath, maskImagePath);
//        mask(outputImage);
        save_image(outputImage, "temp.jpg");
        WeightFunction weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        PixelConnectivity pixelConnectivity = PixelConnectivityFactory.getPixelConnectivity(connectivityType);
        HoleImage holeImage = new HoleImage(outputImage, weightFunction, pixelConnectivity);
        holeImage.fixHoles();
        ImageUtils.save_image(outputImage, OUTPUT_FILE_NAME);
    }

    private static void mask(Mat outputImage) {
        for(int i = 30; i< 130; i++){
            for(int j = 40; j< 60; j++){
                outputImage.put(i, j, 0.0);
            }
        }
    }

    private static Integer getConnectivityType(String arg) {
        try{
            return Integer.valueOf(arg);
        }catch (NumberFormatException e){
            throw new BadRequestException(POWER_FACTOR + MUST_BE_A_VALID_NUMBER);
        }
    }

    private static Double getEpsilon(String arg) {
        try{
            double epsilon =  Double.valueOf(arg);
            if(epsilon <= MINIMAL_EPSILON_ALLOWED) throw new BadRequestException(EPSILON + MUST_BE_POSITIVE);
            return epsilon;
        }catch (NumberFormatException e){
            throw new BadRequestException(EPSILON + MUST_BE_A_VALID_NUMBER);
        }

    }

    private static Double getPowerFactor(String arg) {
        try{
            return Double.valueOf(arg);
        }catch (NumberFormatException e){
            throw new BadRequestException(POWER_FACTOR + MUST_BE_A_VALID_NUMBER);
        }
    }

    private static void validateInput(String[] args)  {
        Utils.CheckNotNull(args, "args");
        if(args.length < MINIMAL_INPUT_SIZE){
            throw new BadRequestException(INPUT_IS_MISSING_ARGUMENTS);
        }
    }

    private static boolean isMaskImageProvided(String[] args) {
        return args.length == INPUT_SIZE_WITH_A_MASK_IMAGE;
    }
}
