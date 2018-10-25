package entities;

import java.util.ArrayList;

/**
 * Created by livne
 * on 25/10/2018.
 */
public class PixelConnectivityFactory {
    public enum PixelConnectivityTypes{
        EIGHT(8),
        FOUR(4);
        private int sign;
        PixelConnectivityTypes(int sign) {
            this.sign = sign;
        }

        public int getSign() {
            return sign;
        }

    }
    public static PixelConnectivity getPixelConnectivity(PixelConnectivityTypes type, ArrayList<ArrayList<Integer>> image)
            throws UnSupportedConnectivityType {
        switch (type){
            case EIGHT:
                return new EightConnected(image);
            case FOUR:
                return new FourConnected(image);
            default:
                throw new UnSupportedConnectivityType();
        }
    }
}
