package code.filters;

import code.controls.AbstractNode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;

import static code.filters.Extractors.brightnessExtractor;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class BrightnessAdjustor implements Filter{

    @Override
    public void apply(AbstractNode node) {

        int initialValue = brightnessExtractor.apply(node.input1.getContent());
        int targetValue = brightnessExtractor.apply(node.input2.getContent());
        Function<Color,Color> colorFunction = new FiltersOfColor.AdjustBrightness(targetValue - initialValue);

        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();

        // ignore alpha for now ?

        node.output1.setContent(new BufferedImage(width,height,TYPE_INT_RGB));
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelInputColor = new Color(node.input1.getContent().getRGB(x,y));
                Color pixelOutputColor = colorFunction.apply(pixelInputColor);

                node.output1.getContent().setRGB(x,y,pixelOutputColor.getRGB());
            }
        }
    }

    @Override
    public boolean checkInput(AbstractNode node) {
        return node.input1.getContent()!=null && node.input2.getContent()!=null;
    }
}
