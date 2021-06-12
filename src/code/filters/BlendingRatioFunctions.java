package code.filters;

import code.controls.SeparatorNode;

import java.awt.*;
import java.util.function.Function;

public class BlendingRatioFunctions {
    public static Function<Color,Double> blendByBrightness = (Color dominant) -> (double)(dominant.getRed() + dominant.getGreen() + dominant.getBlue()) / 765;
    public static Function<Color,Double> blendByDarkness = (Color dominant) -> 1 - (double)(dominant.getRed() + dominant.getGreen() + dominant.getBlue()) / 765;
    public static Function<Color,Double> greenScreen = (Color dominant) -> {
        final int hueGreen = 85;
//        final int maxHueDiff = 42;
        int hue = SeparatorNode.HSVSeparator.getX(dominant.getRGB()) & 0x000000ff;
        int sat = SeparatorNode.HSVSeparator.getY(dominant.getRGB()) & 0x000000ff;
        int hueDiff = Math.abs(hue - hueGreen);
        return FiltersOfColor.toRange((double)(6 * hueDiff + (255 - sat))/255);
    };
    public static Function<Color,Double> blendBySaturation = (Color dominant) -> {
        int red = dominant.getRed();
        int green = dominant.getGreen();
        int blue = dominant.getBlue();

        int max = Math.max(Math.max(red,green),blue);
        int min = Math.min(Math.min(red,green),blue);

        if(max + min == 0) {
            return 0.0;
        }
        return (double)(max - min) / (max + min );
    };
}
