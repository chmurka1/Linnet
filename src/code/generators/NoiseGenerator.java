package code.generators;

import code.controls.GeneratorNode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NoiseGenerator implements Generator{

    int width;
    int height;
    public NoiseGenerator(int width, int height){
        this.width=width;
        this.height=height;
    }
    @Override
    public void generate(GeneratorNode node, String colorInHex) {
        //TODO
    }
}
