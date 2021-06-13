package code.filters;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;


public class FiltersOfColor {

    public static class ColorBlend implements BiFunction<Color,Color,Color> {
        private final Function<Color,Double> blendBy;
        ColorBlend(Function<Color,Double> blendBy) {
            this.blendBy = blendBy;
        }

        @Override
        public java.awt.Color apply(Color dominant, Color recessive) {
            double ratio = blendBy.apply(dominant);

            int red = (int)(dominant.getRed() * ratio + recessive.getRed() * (1-ratio));
            int green = (int)(dominant.getGreen() * ratio + recessive.getGreen() * (1-ratio));
            int blue = (int)(dominant.getBlue() * ratio + recessive.getBlue() * (1-ratio));
            return new Color(RGBUtils.toRange(red), RGBUtils.toRange(green), RGBUtils.toRange(blue));
        }
    }

    public static class AdjustBrightness implements Function<Color,Color>{

        private final int value;

        AdjustBrightness(int value){
            this.value = value/3;
        }
        @Override
        public Color apply(Color color) {
            int red = color.getRed() + value;
            int green = color.getGreen() + value;
            int blue = color.getBlue() + value;
            return new Color(RGBUtils.toRange(red), RGBUtils.toRange(green), RGBUtils.toRange(blue));
        }
    }

    public static BiFunction<ArrayList<Color>,Integer,Color> sharpenParam = (ArrayList<Color> matrix, Integer coefficient) -> {
        float co = (float)coefficient/32;

        int red = (int) (- co*matrix.get(1).getRed() - co*matrix.get(3).getRed() + (1+4*co) * matrix.get(4).getRed() - co*matrix.get(5).getRed() - co*matrix.get(7).getRed());
        int green = (int) (- co*matrix.get(1).getGreen() - co*matrix.get(3).getGreen() + (1+4*co) * matrix.get(4).getGreen() - co*matrix.get(5).getGreen() - co*matrix.get(7).getGreen());
        int blue = (int) (- co*matrix.get(1).getBlue() - co*matrix.get(3).getBlue() + (1+4*co) * matrix.get(4).getBlue() - co*matrix.get(5).getBlue() - co*matrix.get(7).getBlue());
        red = RGBUtils.toRange(red);
        green = RGBUtils.toRange(green);
        blue = RGBUtils.toRange(blue);
        return new Color(red,green,blue);
    };

    public static BiFunction<Color,Integer,Color> contrastParam = (Color color,Integer coefficient) -> {

        float factor =  (float)(259 * (coefficient + 255)) / (255 * (259 - coefficient));

        int red = (int)(factor*(color.getRed() - 128)) + 128;
        int green = (int)(factor*(color.getGreen() - 128)) + 128;
        int blue = (int)(factor*(color.getBlue() - 128)) + 128;

        red = RGBUtils.toRange(red);
        green = RGBUtils.toRange(green);
        blue = RGBUtils.toRange(blue);

        return new Color(red,green,blue);
    };
    public static BiFunction<Color,Integer,Color> saturateParam = (Color color,Integer coefficient) -> {

        int maxEffect = 100;

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        int avg = (red+green+blue)/3;

        red += (red - avg) * coefficient/maxEffect;
        green += (green - avg) * coefficient/maxEffect;
        blue += (blue - avg) * coefficient/maxEffect;

        return new Color(RGBUtils.toRange(red), RGBUtils.toRange(green), RGBUtils.toRange(blue));
    };
}
