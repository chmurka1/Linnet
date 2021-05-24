package code.filters;

import code.controls.NodeControl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.BiFunction;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class FilterParametrized implements Filter{
    /** takes pixel (x,y) from input to determine (x,y) in output */

    BiFunction<Color,Integer,Color> colorFunction;
    Integer coefficient;
    FilterParametrized(BiFunction<Color,Integer,Color> colorFunction,Integer coefficient){
        this.colorFunction = colorFunction;
        this.coefficient = coefficient;
    }

    @Override
    public void apply(NodeControl node) {

        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();

        node.output1.setContent(new BufferedImage(width,height,TYPE_INT_RGB));
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelInputColor = new Color(node.input1.getContent().getRGB(x,y));
                Color pixelOutputColor = colorFunction.apply(pixelInputColor,coefficient);

                node.output1.getContent().setRGB(x,y,pixelOutputColor.getRGB());
            }
        }
    }

    @Override
    public boolean checkInput(NodeControl node) {
        return node.input1.getContent()!=null;
    }
}

