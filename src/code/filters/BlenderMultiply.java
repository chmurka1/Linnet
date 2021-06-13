package code.filters;

import code.controls.AbstractNode;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class BlenderMultiply implements Filter{

    @Override
    public void apply(AbstractNode node) {

        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();

        node.output1.setContent(new BufferedImage(width,height,TYPE_INT_RGB));
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color1 = node.input1.getContent().getRGB(x,y);
                int color2 = node.input2.getContent().getRGB(x,y);

                node.output1.getContent().setRGB(x,y,RGBUtils.redComponent(color1)*RGBUtils.redComponent(color2)/256 << 16
                | RGBUtils.greenComponent(color1)*RGBUtils.greenComponent(color2)/256 << 8
                | RGBUtils.blueComponent(color1)*RGBUtils.blueComponent(color2)/256);
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
