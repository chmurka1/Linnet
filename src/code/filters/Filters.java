package code.filters;



import java.awt.*;
import java.awt.image.BufferedImage;


public class Filters {
    Filter brightenImage = new code.filters.FilterType1(Color::brighter);
    Filter darkenImage = new code.filters.FilterType1(Color::darker);

}
