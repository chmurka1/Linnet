package starting;

public abstract class Feature {

    double featureValue = 0;
    /** w tej paczce javafx.scene.paint.Color wszystkie wartosci są CHYBA na double */

    Feature(double featureValue){
        this.featureValue = featureValue;

    }

    public double getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(double suggestedValue) {
        throw new UnsupportedOperationException();
    }

}
