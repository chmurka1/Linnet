package starting;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.function.Function;

public class Extractors {
    public static final Function<ArrayList<Image>, Feature> brightness1 = (ArrayList<Image> inputImages) -> {

        if(inputImages.isEmpty()){
            throw new NullPointerException();
        }

        double averageBrightness;
        double sum = 0;

        for(Image image : inputImages){
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();

            //TODO nie wiem czy to najlepszy pomysl, czy te double sie dobrze dodaja ?


            double brightnessSumOfImage = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    brightnessSumOfImage += image.getPixelReader().getColor(x,y).getBrightness();
                }
            }
            sum = brightnessSumOfImage/image.getWidth()/image.getHeight();
        }
        averageBrightness = sum/inputImages.size();


        return new Brightness(averageBrightness);
    };
}
//
//    Class<T> featureType;
//
//    Extractor(Class<T> typeTag) {
//        featureType = typeTag;
//    }
//
//    public Class<T> getType() {
//        return featureType;
//    }
