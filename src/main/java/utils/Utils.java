package utils;


public class Utils {
    private static final String MUST_NOT_BE_NULL_MSG = "must not be null.";
    private static final String MUST_NOT_BE_EMPTY_MSG = "must not be empty.";
    public static final String EMPTY_STRING = "";

    public static void CheckNotNull(Object object, String objectName) throws BadInputException {
        if(object == null){
            throw new BadInputException(objectName + " " + MUST_NOT_BE_NULL_MSG);
        }
    }

    public static void CheckNotEmptyString(String object, String objectName) throws BadInputException {
        if(object.equals(EMPTY_STRING)){
            throw new BadInputException(objectName + " " + MUST_NOT_BE_EMPTY_MSG);
        }
    }


}
