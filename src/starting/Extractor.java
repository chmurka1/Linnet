package starting;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.function.Function;

public abstract class Extractor<T extends Feature> implements Function<ArrayList<Image>, Double> {

    Class<T> featureType;

    Extractor(Class<T> typeTag) {
        featureType = typeTag;
    }

    public Class<T> getType() {
        return featureType;
    }
}
