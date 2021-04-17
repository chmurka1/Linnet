package starting;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Brightness1 extends Extractor<Brightness>{

    Brightness1(Feature feature) {
        super(feature);
    }

    /**
     * Applies this function to the given argument.
     *
     * @param inputImages the function argument
     * @return the function result
     */
    @Override
    public Double apply(ArrayList<Image> inputImages) {

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


        return averageBrightness;
    }
}
