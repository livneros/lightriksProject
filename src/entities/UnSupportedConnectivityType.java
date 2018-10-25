package entities;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class UnSupportedConnectivityType extends Exception {
    public static final String MSG = "unsupported connectivity type.";

    public UnSupportedConnectivityType() {
        super(MSG);
    }
}
