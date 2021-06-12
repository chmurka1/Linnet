package code.filters;

import java.awt.*;
import java.util.function.Function;

public class BlendingRatioFunctions {
    public static Function<Color,Double> blendByBrightness = (Color dominant) -> (double)(dominant.getRed() + dominant.getGreen() + dominant.getBlue()) / 765;
    public static Function<Color,Double> blendByDarkness = (Color dominant) -> 1 - (double)(dominant.getRed() + dominant.getGreen() + dominant.getBlue()) / 765;
    public static Function<Color,Double> greenScreen = (Color dominant) -> (double)(Math.min(255,dominant.getRed() + dominant.getBlue() + 128 - dominant.getGreen())) /255;
    public static Function<Color,Double> blendBySaturation = (Color dominant) -> {

        int red = dominant.getRed();
        int green = dominant.getGreen();
        int blue = dominant.getBlue();

        int max = Math.max(Math.max(red,green),blue);
        int min = Math.min(Math.min(red,green),blue);

        return (double)(max - min) / (max + min);
    };
}
