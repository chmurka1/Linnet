package code.filters;



import java.awt.*;
import java.awt.image.BufferedImage;


public class Filters {
    public static Filter brightenImage = new code.filters.FilterType1(Color::brighter);
    public static Filter darkenImage = new code.filters.FilterType1(Color::darker);
    public static Filter blackAndWhite = new code.filters.FilterType1(FiltersOfColor.toGrey);
    public static Filter saturate = new code.filters.FilterType1(FiltersOfColor.toColors);
}
