package code.controls;


import java.awt.*;




public class Filters {
    Filter brightenImage = new FilterType1(Color::brighter);
    Filter darkenImage = new FilterType1(Color::darker);

    public static Filter adjustingBrightness(int value) {
        return new FilterType1(new FiltersOfColor.AdjustBrightness(value));
    }
}
