package code.filters;

import code.controls.NodeControl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.BiFunction;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class BlenderOfTwo implements Filter{
    @Override
    public void apply(NodeControl node) {

        BiFunction<Color,Color,Color> colorFunction = FiltersOfColor.colorBlend;

        int width = node.in1.getWidth();
        int height = node.in1.getHeight();

        // ignore alpha for now ?

        node.out1 = new BufferedImage(width,height,TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelInput1Color = new Color(node.in1.getRGB(x,y));
                Color pixelInput2Color = new Color(node.in2.getRGB(x,y));
                Color pixelOutputColor = colorFunction.apply(pixelInput1Color,pixelInput2Color);

                node.out1.setRGB(x,y,pixelOutputColor.getRGB());
            }
        }
    }

    @Override
    public boolean checkInput(NodeControl node) {
        if(node.in1 == null || node.in2 == null){
            return false;
        }
        if(node.in1.getWidth() != node.in2.getWidth()){
            System.out.println("width not compatible");
            return false;
        }
        if(node.in1.getHeight() != node.in2.getHeight()){
            System.out.println("height not compatible");
            return false;
        }
        return true;
    }
}
