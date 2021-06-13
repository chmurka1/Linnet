package code.filters;

import code.controls.AbstractNode;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ResizeWidth implements Filter {

    private final int newWidth;

    public ResizeWidth(int newWidth){
        this.newWidth = newWidth;
    }

    @Override
    public void apply(AbstractNode node) {

        long start = System.currentTimeMillis();
        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();

        BufferedImage input = node.input1.getContent();

        int [][] inputMap =  new int [width][height];

        for( int i = 0; i < width; i++ ) {
            for( int j = 0; j < height; j++ ) {
                inputMap[i][j] = input.getRGB(i, j);
            }
        }

        double factor = (double)width/newWidth;

        BufferedImage out = new BufferedImage(newWidth,height,TYPE_INT_RGB);
        for (int x = 0; x < newWidth; x++) {

            double floor = Math.floor(factor*x);
            int floorInt = (int)floor;
            double mantissa = factor*x - floor;
            double antymantissa = 1-mantissa;
            int ceiling  = Math.min(floorInt+1,width-1);

            for (int y = 0; y < height; y++) {
                int leftPixel = inputMap[floorInt][y];
                int rightPixel = inputMap[ceiling][y];

                int red = (int) (RGBUtils.redComponent(leftPixel) * antymantissa + RGBUtils.redComponent(rightPixel) * mantissa);
                int green = (int)(RGBUtils.greenComponent(leftPixel) * antymantissa + RGBUtils.greenComponent(rightPixel) * mantissa);
                int blue = (int)(RGBUtils.blueComponent(leftPixel) * antymantissa + RGBUtils.blueComponent(rightPixel) * mantissa);


                out.setRGB(x,y,(RGBUtils.toRange(red) << 16) + (RGBUtils.toRange(green) << 8) + RGBUtils.toRange(blue));
            }
        }
        node.output1.setContent(out);
        System.out.println(this.getClass() + " " + (System.currentTimeMillis()-start));
    }

    @Override
    public boolean checkInput(AbstractNode node) {
        return node.input1.getContent()!=null;
    }
}
