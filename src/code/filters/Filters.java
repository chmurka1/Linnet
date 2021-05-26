package code.filters;

import java.awt.*;

public class Filters {
    public static Filter brightenImage = new code.filters.FilterType1(Color::brighter);
    public static Filter darkenImage = new code.filters.FilterType1(Color::darker);
    public static Filter sharp = new code.filters.FilterParametrized3x3(FiltersOfColor.sharpen, 1);
    public static Filter contrast = new code.filters.FilterParametrized(FiltersOfColor.contrast,8);
    public static Filter empty = new EmptyFilter();
}
