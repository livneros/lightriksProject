import entities.*;
import org.opencv.core.Mat;
import utils.Constants;
import utils.ImageUtils;
import utils.exceptions.MaskImageSizeException;

import java.io.IOException;

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

    public static void main(String[] args) throws IOException, UnSupportedConnectivityType, MaskImageSizeException, BadInputException {
        validateInput(args);
        double powerFactor = getPowerFactor(args[POWER_FACTOR_INDEX]);
        double epsilon = getEpsilon(args[EPSILON_INDEX]);
        int connectivityType = getConnectivityType(args[CONNECTIVITY_TYPE_INDEX]);
        String imagePath = args[IMAGE_PATH_INDEX];
        String maskImagePath = getMaskPathIfExists(args);
        Mat outputImage = ImageUtils.getMaskedImage(imagePath, maskImagePath);
        WeightFunction weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        PixelConnectivity pixelConnectivity = PixelConnectivityFactory.getPixelConnectivity(connectivityType);
        AlgorithmSolver algorithmSolver = new AlgorithmSolver(outputImage, weightFunction, pixelConnectivity);
        algorithmSolver.fixHoles();
        ImageUtils.save_image(outputImage, OUTPUT_FILE_NAME);
    }

    private static String getMaskPathIfExists(String[] args) {
        if(isMaskImageProvided(args)){
            return args[MASK_IMAGE_PATH_INDEX];
        }
        return null;
    }

    private static Integer getConnectivityType(String arg) throws BadInputException {
        try{
            return Integer.valueOf(arg);
        }catch (NumberFormatException e){
            throw new BadInputException(POWER_FACTOR + MUST_BE_A_VALID_NUMBER);
        }
    }

    private static Double getEpsilon(String arg) throws BadInputException {
        try{
            double epsilon =  Double.valueOf(arg);
            if(epsilon <= Constants.MINIMAL_EPSILON_ALLOWED) throw new BadInputException(EPSILON + MUST_BE_POSITIVE);
            return epsilon;
        }catch (NumberFormatException e){
            throw new BadInputException(EPSILON + MUST_BE_A_VALID_NUMBER);
        }

    }

    private static Double getPowerFactor(String arg) throws BadInputException {
        try{
            return Double.valueOf(arg);
        }catch (NumberFormatException e){
            throw new BadInputException(POWER_FACTOR + MUST_BE_A_VALID_NUMBER);
        }
    }

    private static void validateInput(String[] args) throws BadInputException {
        if(args.length < MINIMAL_INPUT_SIZE){
            throw new BadInputException(INPUT_IS_MISSING_ARGUMENTS);
        }
    }

    private static boolean isMaskImageProvided(String[] args) {
        return args.length == INPUT_SIZE_WITH_A_MASK_IMAGE;
    }
}
