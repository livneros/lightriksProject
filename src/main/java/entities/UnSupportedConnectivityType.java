package entities;

/**
 * Created by Livne
 * on 25/10/2018.
 */
public class UnSupportedConnectivityType extends Exception {
    private static final String MSG = "unsupported connectivity type.";

    UnSupportedConnectivityType() {
        super(MSG);
    }
}
