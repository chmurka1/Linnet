package starting;

public class Brightness extends Feature {


    Brightness(int featureValue) {
        super(featureValue);
    }

    @Override
    public int getFeatureValue() {
        return featureValue;
    }

    @Override
    public void setFeatureValue(int suggestedValue) {
        //TODO restrictions?
        featureValue = suggestedValue;
    }
}
