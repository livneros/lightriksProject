package entities;

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

    public static PixelConnectivity getPixelConnectivity(int type)
            throws UnSupportedConnectivityType {
        switch (type){
            case 8:
                return new EightConnected();
            case 4:
                return new FourConnected();
            default:
                throw new UnSupportedConnectivityType();
        }
    }
}
