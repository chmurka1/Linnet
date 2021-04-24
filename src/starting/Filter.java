package starting;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Filter implements Function<BufferedImage,BufferedImage> {

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
    public BufferedImage apply(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        //TODO ignoring alpha for now ?

        BufferedImage outputImage = new BufferedImage(width,height,TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelInputColor = new Color(inputImage.getRGB(x,y));
                Color pixelOutputColor = oneToOne.apply(pixelInputColor);

                outputImage.setRGB(x,y,pixelOutputColor.getRGB());
            }
        }
        return outputImage;
    }
}