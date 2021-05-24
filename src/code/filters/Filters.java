package code.filters;



import java.awt.*;


public class Filters {
    public static Filter brightenImage = new code.filters.FilterType1(Color::brighter);
    public static Filter darkenImage = new code.filters.FilterType1(Color::darker);
    public static Filter blackAndWhite = new code.filters.FilterType1(FiltersOfColor.toGrey);
    public static Filter saturate = new code.filters.FilterType1(FiltersOfColor.toColors);
    public static Filter sharp = new code.filters.FilterParametrized3x3(FiltersOfColor.sharpen, 1);
    public static Filter contrast = new code.filters.FilterParametrized(FiltersOfColor.contrast,8);
}
