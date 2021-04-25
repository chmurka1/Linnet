package code.controls;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class FilterType1 extends Filter{

    /** takes pixel (x,y) from input to determine (x,y) in output */

    Function<Color,Color> colorFunction;
    FilterType1(Function<Color,Color> colorFunction){
        this.colorFunction = colorFunction;
    }

    @Override
    public BufferedImage apply(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // ignore alpha for now ?

        BufferedImage outputImage = new BufferedImage(width,height,TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelInputColor = new Color(inputImage.getRGB(x,y));
                Color pixelOutputColor = colorFunction.apply(pixelInputColor);

                outputImage.setRGB(x,y,pixelOutputColor.getRGB());
            }
        }
        return outputImage;
    }
}
