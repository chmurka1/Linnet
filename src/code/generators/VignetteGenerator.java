package code.generators;

import code.controls.GeneratorNode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VignetteGenerator implements Generator{

    int width;
    int height;
    public VignetteGenerator(int width, int height){
        this.width=width;
        this.height=height;
    }
    @Override
    public void generate(GeneratorNode node, String colorInHex) {
        BufferedImage img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.decode(colorInHex));
        g2d.fillRect(0,0, width, height);
        g2d.setColor(new Color(255,255,255));
        g2d.fillOval(width/20, height/20, 9*width/10, 9*height/10);
        g2d.dispose();

        node.output.setContent(img);
    }
}
