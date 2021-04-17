package starting;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.function.Function;

public class Filter implements Function<Image,Image> {

    /**
     *  ustawia pixel (x,y) w output
     *  tylko na podstawie pixela (x,y) w input */
    Function<Color,Color> oneToOne;

    //TODO (?) Function<ArrayList<Color>,Color> manyToOne;

    /**
     * Applies this function to the given argument.
     *
     * @param inputImage the function argument
     * @return the function result
     */
    @Override
    public Image apply(Image inputImage) {
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();

        WritableImage outputImage = new WritableImage(width,height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelInputColor = inputImage.getPixelReader().getColor(x,y);
                Color pixelOutputColor = oneToOne.apply(pixelInputColor);

                outputImage.getPixelWriter().setColor(x,y,pixelOutputColor);
            }
        }
        return outputImage;
    }
}