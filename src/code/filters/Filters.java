package code.filters;

import java.awt.*;

public class Filters {
    public static Filter brightenImage = new code.filters.FilterType1(Color::brighter);
    public static Filter darkenImage = new code.filters.FilterType1(Color::darker);

    // advised range from -32 to +256
    public static Filter sharpen(int coefficient) {
        return new code.filters.FilterParametrized3x3(FiltersOfColor.sharpenParam, coefficient);
    }

    // RANGE ALLOWED from -256 to +256
    // advised range from -128 to +128
    public static Filter contrast(int coefficient) {
        return new code.filters.FilterParametrized(FiltersOfColor.contrastParam,coefficient);
    }

    // advised range from -128 to +128
    public static Filter saturate(int coefficient) {
        return new code.filters.FilterParametrized(FiltersOfColor.saturateParam,coefficient);
    }
//    public static Filter boxBlur(int coefficient) {
//        return new code.filters.FilterParametrized3x3(FiltersOfColor.boxBlur,coefficient);
//    }

    public static Filter empty = new EmptyFilter();
}
