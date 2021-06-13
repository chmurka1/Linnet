package code.filters;

import code.controls.AbstractNode;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class HorizontalBlur implements Filter{

    int sizeInPerMille;
    int size;//pixels

    public HorizontalBlur(Integer coefficient) {
        this.sizeInPerMille = Math.abs(coefficient);
    }

    @Override
    public void apply(AbstractNode node) {

        long start = System.currentTimeMillis();
        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();
        size = width*sizeInPerMille/1000;

        node.output1.setContent(new BufferedImage(width,height,TYPE_INT_RGB));

        int [][] colors = new int [width+2*size][height+2*size];

        for (int y = 0; y < height + 2*size; y++) {
            for (int x = 0; x < width + 2*size; x++) {
                if(x<size || x>=width+size || y<size || y>=height+size) {
                    colors[x][y] = 0x808080;
                }
                else {
                    colors[x][y] = node.input1.getContent().getRGB(x-size,y-size);
                }
            }
        }
        int div = 2*size+1;
        for (int y = 0; y < height; y++) {
            int sumR = 128*size;
            int sumG = 128*size;
            int sumB = 128*size;
            for(int i=0;i<=size;i++) {
                int pixel = colors[size+i][y+size];
                sumR += RGBUtils.redComponent(pixel);
                sumG += RGBUtils.greenComponent(pixel);
                sumB += RGBUtils.blueComponent(pixel);
            }
            int avgR = sumR/div;
            int avgG = sumG/div;
            int avgB = sumB/div;
            node.output1.getContent().setRGB(0,y,avgR << 16 | avgG << 8 | avgB);

            for (int x = 1; x < width; x++) {
                int rightPixel = colors[x+2*size][y+size];
                int leftPixel = colors[x-1][y+size];

                sumR = sumR + RGBUtils.redComponent(rightPixel) - RGBUtils.redComponent(leftPixel);
                sumG = sumG + RGBUtils.greenComponent(rightPixel) - RGBUtils.greenComponent(leftPixel);
                sumB = sumB + RGBUtils.blueComponent(rightPixel) - RGBUtils.blueComponent(leftPixel);
                avgR = sumR/div;
                avgG = sumG/div;
                avgB = sumB/div;

                node.output1.getContent().setRGB(x,y,avgR << 16 | avgG << 8 | avgB);
            }
        }
        System.out.println(this.getClass() + " " + (System.currentTimeMillis()-start));
    }

    @Override
    public boolean checkInput(AbstractNode node) {
        return node.input1.getContent()!=null;
    }
}
