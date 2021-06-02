package code.generators;

import code.controls.GeneratorNode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlainGenerator implements Generator{

    int width;
    int height;
    public PlainGenerator(int width, int height){
        this.width=width;
        this.height=height;
    }
    @Override
    public void generate(GeneratorNode node) {
        //for now only red
        BufferedImage testImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = testImg.createGraphics();
        g2d.setColor(Color.red);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();

        node.output.setContent(testImg);
    }
}
