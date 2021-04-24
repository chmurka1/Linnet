package starting;

public abstract class Feature {

    int featureValue = 0;


    Feature(int featureValue){
        this.featureValue = featureValue;

    }

    public int getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(int suggestedValue) {
        throw new UnsupportedOperationException();
    }

}
