package entities;

/**
 * Created by Livne
 * on 25/10/2018.
 */
public class PixelConnectivityFactory {
    public enum PixelConnectivityTypes{
        EIGHT(8),
        FOUR(4);
        private final int value;
        PixelConnectivityTypes(int value) {
            this.value = value;
        }
        public int getValue(){return value;}
    }

    public static PixelConnectivity getPixelConnectivity(int type)
            throws UnSupportedConnectivityType {
        if (type == PixelConnectivityTypes.EIGHT.getValue()) {
            return new EightConnected();
        }
        else if(type == PixelConnectivityTypes.FOUR.getValue()){
            return new FourConnected();
        }
        else{
            throw new UnSupportedConnectivityType();
        }
    }
}
