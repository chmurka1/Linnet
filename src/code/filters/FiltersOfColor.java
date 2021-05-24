package code.filters;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;


public class FiltersOfColor {

    public static Function<Color,Color> toGrey = (Color color) -> {
        int i = (color.getRed() + color.getGreen() + color.getBlue())/3;
        return new Color(i,i,i);
    };

    public static BiFunction<Color,Color,Color> colorBlend = (Color dominant, Color recessive) -> {

        double ratio = (double)(dominant.getRed() + dominant.getGreen() + dominant.getBlue()) / 765;

        //TODO not sure if it is safe : can it be out 0-255 range ?
        int red = (int)(dominant.getRed() * ratio + recessive.getRed() * (1-ratio));
        int green = (int)(dominant.getGreen() * ratio + recessive.getGreen() * (1-ratio));
        int blue = (int)(dominant.getBlue() * ratio + recessive.getBlue() * (1-ratio));

        return new Color(red,green,blue);
    };
    public static Function<Color, Color> toColors = (Color color) -> {
        int i = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        int red = Math.max(Math.min(2 * color.getRed() - i, 255), 0);
        int green = Math.max(Math.min(2 * color.getGreen() - i, 255), 0);
        int blue = Math.max(Math.min(2 * color.getBlue() - i, 255), 0);
        return new Color(red, green, blue);
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

    public static BiFunction<ArrayList<Color>,Integer,Color> sharpen = (ArrayList<Color> matrix, Integer coefficient) -> {
        int co = (int)(double) coefficient;
        int red = - co*matrix.get(1).getRed() - co*matrix.get(3).getRed() + (1+4*co) * matrix.get(4).getRed() - co*matrix.get(5).getRed() - co*matrix.get(7).getRed();
        int green = - co*matrix.get(1).getGreen() - co*matrix.get(3).getGreen() + (1+4*co) * matrix.get(4).getGreen() - co*matrix.get(5).getGreen() - co*matrix.get(7).getGreen();
        int blue = - co*matrix.get(1).getBlue() - co*matrix.get(3).getBlue() + (1+4*co) * matrix.get(4).getBlue() - co*matrix.get(5).getBlue() - co*matrix.get(7).getBlue();
        red = Math.min(Math.max(red,0),255);
        green = Math.min(Math.max(green,0),255);
        blue = Math.min(Math.max(blue,0),255);
        return new Color(red,green,blue);
    };
    public static BiFunction<Color,Integer,Color> contrast = (Color color,Integer coefficient) -> {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        int brightnessDif = (((red + green + blue)/3 - 128) * coefficient) >> 4;
        red = Math.min(255,Math.max(0,red + brightnessDif));
        green = Math.min(255,Math.max(0,green + brightnessDif));
        blue = Math.min(255,Math.max(0,blue + brightnessDif));

        return new Color(red,green,blue);
    };

}
