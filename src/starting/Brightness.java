package starting;

public class Brightness extends Feature {

    @Override
    public double getFeatureValue() {
        return featureValue;
    }

    @Override
    public void setFeatureValue(double suggestedValue) {
        if(suggestedValue > 0 && suggestedValue < 1){
            featureValue = suggestedValue;
        }
    }
}
