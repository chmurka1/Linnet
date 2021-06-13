package code.filters;

import code.controls.AbstractNode;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ResizeHeight implements Filter {
    int newHeight;
    public ResizeHeight(int newHeight){
        this.newHeight = newHeight;
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

        double factor = (double)height/newHeight;

        BufferedImage out = new BufferedImage(width,newHeight,TYPE_INT_RGB);
        for (int y = 0; y < newHeight; y++) {

            double floor = Math.floor(factor*y);
            int floorInt = (int)floor;
            double mantissa = factor*y - floor;
            double antymantissa = 1-mantissa;
            int ceiling  = Math.min(floorInt+1,height-1);

            for (int x = 0; x < width; x++) {
                int upPixel = inputMap[x][floorInt];
                int bottomPixel = inputMap[x][ceiling];
                int red = (int) (RGBUtils.redComponent(upPixel) * antymantissa + RGBUtils.redComponent(bottomPixel) * mantissa);
                int green = (int)(RGBUtils.greenComponent(upPixel) * antymantissa + RGBUtils.greenComponent(bottomPixel) * mantissa);
                int blue = (int)(RGBUtils.blueComponent(upPixel) * antymantissa + RGBUtils.blueComponent(bottomPixel) * mantissa);

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
