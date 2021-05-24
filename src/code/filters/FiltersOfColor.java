package code.filters;

import java.awt.*;
import java.util.function.BiFunction;
import java.util.function.Function;


public class FiltersOfColor {

    public static BiFunction<Color,Color,Color> colorBlend = (Color dominant, Color recessive) -> {

        double ratio = (double)(dominant.getRed() + dominant.getGreen() + dominant.getBlue()) / 765;

        //TODO not sure if it is safe : can it be out 0-255 range ?
        int red = (int)(dominant.getRed() * ratio + recessive.getRed() * (1-ratio));
        int green = (int)(dominant.getGreen() * ratio + recessive.getGreen() * (1-ratio));
        int blue = (int)(dominant.getBlue() * ratio + recessive.getBlue() * (1-ratio));

        return new Color(red,green,blue);
    };

    public static class AdjustBrightness implements Function<Color,Color>{

        int value;

        AdjustBrightness(int value){
            this.value = value;
        }
        @Override
        public Color apply(Color color) {
            int red = Math.max(Math.min(color.getRed()+value/3, 255),0);
            int green = Math.max(Math.min(color.getGreen()+value/3, 255),0);
            int blue = Math.max(Math.min(color.getBlue()+value/3, 255),0);
            return new Color(red,green,blue);
        }
    }
}
