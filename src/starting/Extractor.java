package starting;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.function.Function;

public abstract class Extractor<T extends Feature> implements Function<ArrayList<Image>, Double> {

    Class<? extends Feature> featureType;

    Extractor(Feature feature){
        featureType = feature.getClass();
    }

    public Class<? extends Feature> getType(){
        return featureType;
    }
}
