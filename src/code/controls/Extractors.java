package code.controls;

import starting.Brightness;
import starting.Feature;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.function.Function;

public class Extractors {
    public static final Function<BufferedImage, Integer> brightnessExtractor = (BufferedImage input) -> {

        int width = input.getWidth();
        int height = input.getHeight();

        int averageBrightness;
        int sum = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelColor = new Color(input.getRGB(x,y));
                sum += (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue());
            }
        }
        averageBrightness = (int)(((double) sum)/width/height);

        return averageBrightness;
    };
}
