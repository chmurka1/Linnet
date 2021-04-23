package starting;

public class Brightness extends Feature {


    /**
     * w tej paczce javafx.scene.paint.Color wszystkie wartosci są CHYBA na double
     *
     * @param featureValue
     */
    Brightness(double featureValue) {
        super(featureValue);
    }

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
