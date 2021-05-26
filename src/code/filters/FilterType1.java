package code.filters;

import code.controls.AbstractNode;

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
    public void apply(AbstractNode node) {

        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();

        // ignore alpha for now ?

        BufferedImage out = new BufferedImage(width,height,TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelInputColor = new Color(node.input1.getContent().getRGB(x,y));
                Color pixelOutputColor = colorFunction.apply(pixelInputColor);

                out.setRGB(x,y,pixelOutputColor.getRGB());
            }
        }
        node.output1.setContent(out);
    }

    @Override
    public boolean checkInput(AbstractNode node) {
        return node.input1.getContent()!=null;
    }
}
