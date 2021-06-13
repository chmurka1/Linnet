package code.generators;

import code.controls.GeneratorNode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.function.Function;

public class NoiseGenerator implements Generator{

    int width;
    int height;
    public NoiseGenerator(int width, int height){
        this.width=width;
        this.height=height;
    }
    @Override
    public void generate(GeneratorNode node, String colorInHex) {
        BufferedImage img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Color color=Color.decode(colorInHex);

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                img.setRGB(x, y, changeSaturation.apply(color).getRGB());
            }
        }

        node.output.setContent(img);
    }

    public static int toRange(int var) {
        return Math.min(Math.max(var,0),255);
    }

    public static Function<Color,Color> changeSaturation = (Color color) -> {

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        Random random = new Random();
        int coef_add = random.nextInt(200);
        int coef_sub = random.nextInt(100);

        if(red <= 80) red += coef_add;
        else red -= coef_sub;
        if(green <= 80) green += coef_add;
        else green -= coef_sub;
        if(blue <= 80) blue += coef_add;
        else blue -= coef_sub;

        return new Color(toRange(red),toRange(green),toRange(blue));
    };

}
