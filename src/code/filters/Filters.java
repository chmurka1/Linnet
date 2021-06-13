package code.filters;

import java.awt.*;

public class Filters {
    public static Filter brightenImage = new FilterSimple(Color::brighter);
    public static Filter darkenImage = new FilterSimple(Color::darker);

    public static Filter sharpen(int coefficient) {
        return new code.filters.FilterParametrized3x3(FiltersOfColor.sharpenParam, coefficient);
    }

    public static Filter contrast(int coefficient) {
        return new code.filters.FilterParametrized(FiltersOfColor.contrastParam,coefficient);
    }

    public static Filter saturate(int coefficient) {
        return new code.filters.FilterParametrized(FiltersOfColor.saturateParam,coefficient);
    }

    public static Filter empty = new EmptyFilter();
}
