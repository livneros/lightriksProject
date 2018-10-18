package utils;

public class Utils {
    private static final String MUST_NOT_BE_NULL_MSG = "must not be null.";

    public static void CheckNotNull(Object object, String objectName) throws Exception {
        if(object == null){
            throw new Exception(objectName + " " + MUST_NOT_BE_NULL_MSG);
        }
    }
}
