package code.filters;

import code.controls.AbstractNode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.BiFunction;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class BlenderOfTwo implements Filter{
    @Override
    public void apply(AbstractNode node) {

        BiFunction<Color,Color,Color> colorFunction = FiltersOfColor.colorBlend;

        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();

        // ignore alpha for now ?

        node.output1.setContent(new BufferedImage(width,height,TYPE_INT_RGB));
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelInput1Color = new Color(node.input1.getContent().getRGB(x,y));
                Color pixelInput2Color = new Color(node.input2.getContent().getRGB(x,y));
                Color pixelOutputColor = colorFunction.apply(pixelInput1Color,pixelInput2Color);

                node.output1.getContent().setRGB(x,y,pixelOutputColor.getRGB());
            }
        }
    }

    @Override
    public boolean checkInput(AbstractNode node) {
        if(node.input1.getContent() == null || node.input2.getContent() == null){
            return false;
        }
        if(node.input1.getContent().getWidth() != node.input2.getContent().getWidth()){
            System.out.println("width not compatible");
            return false;
        }
        if(node.input1.getContent().getHeight() != node.input2.getContent().getHeight()){
            System.out.println("height not compatible");
            return false;
        }
        return true;
    }
}
