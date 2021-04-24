package starting;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.function.Function;

public class Extractors {
    public static final Function<ArrayList<BufferedImage>, Feature> brightness1 = (ArrayList<BufferedImage> inputImages) -> {

        if(inputImages.isEmpty()){
            throw new NullPointerException();
        }

        double averageBrightness;
        double sum = 0;

        for(BufferedImage image : inputImages){
            int width = image.getWidth();
            int height = image.getHeight();

            //TODO nie wiem czy to najlepszy pomysl, czy te double sie dobrze dodaja ?


            int brightnessSumOfImage = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color pixelColor = new Color(image.getRGB(x,y));
                    brightnessSumOfImage += (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue());
                }
            }
            sum += ((double) brightnessSumOfImage)/image.getWidth()/image.getHeight();
        }
        averageBrightness = sum/inputImages.size();


        return new Brightness((int)averageBrightness);
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
