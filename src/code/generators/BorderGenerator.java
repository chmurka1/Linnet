package code.generators;

import code.controls.GeneratorNode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BorderGenerator implements Generator{

    int width;
    int height;
    public BorderGenerator(int width, int height){
        this.width=width;
        this.height=height;
    }
    @Override
    public void generate(GeneratorNode node, String colorInHex) {
        BufferedImage img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(y<height/10 || y>height*9/10 || x<width/10 || x>width*9/10)
                    img.setRGB(x,y,Color.decode(colorInHex).getRGB());
                else
                    img.setRGB(x,y,Color.green.getRGB());
            }
        }

        node.output.setContent(img);
    }
}
