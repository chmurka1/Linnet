package code.filters;

import code.controls.NodeControl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class FilterType1 implements Filter{
    /** takes pixel (x,y) from input to determine (x,y) in output */

    Function<Color,Color> colorFunction;
    FilterType1(Function<Color,Color> colorFunction){
        this.colorFunction = colorFunction;
    }

    @Override
    public void apply(NodeControl node) {

        int width = node.in1.getWidth();
        int height = node.in1.getHeight();

        // ignore alpha for now ?

        node.out1 = new BufferedImage(width,height,TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelInputColor = new Color(node.in1.getRGB(x,y));
                Color pixelOutputColor = colorFunction.apply(pixelInputColor);

                node.out1.setRGB(x,y,pixelOutputColor.getRGB());
            }
        }
    }

    @Override
    public boolean checkInput(NodeControl node) {
        return node.in1!=null;
    }
}
