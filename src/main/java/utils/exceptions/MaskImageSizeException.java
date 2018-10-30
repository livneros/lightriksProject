package utils.exceptions;

/**
 * Created by Livne
 * on 29/10/2018.
 */
public class MaskImageSizeException extends Exception {
    private static final String MASK_IMAGE_INVALID_SIZE = "The mask image must not be bigger than the original image";

    public MaskImageSizeException() {
        super(MASK_IMAGE_INVALID_SIZE);
    }
}
