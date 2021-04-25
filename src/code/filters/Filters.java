package code.filters;



import java.awt.*;
import java.awt.image.BufferedImage;


public class Filters {
    public static Filter brightenImage = new code.filters.FilterType1(Color::brighter);
    public static Filter darkenImage = new code.filters.FilterType1(Color::darker);
}
